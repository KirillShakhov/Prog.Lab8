package lab;

import lab.BasicClasses.MusicBand;
import lab.Commands.CommandInvoker;
import lab.Commands.CommandReceiver;
import lab.Commands.ConcreteCommands.*;
import lab.Commands.SerializedCommands.Message;
import lab.Commands.Utils.Creaters.ElementCreator;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class ClientController implements Runnable {
	private static final int BUFF_SIZE = 1000000;
	public static String name = null;
	public static String pass = null;
	static String hostname;
	static int port;

	static Scanner scanner = new Scanner(System.in);
	static boolean isAuth = false;
	static int reconect_schetchick = 1;
	static SocketChannel client;

	static CommandInvoker commandInvoker = new CommandInvoker();
	ElementCreator elementCreator = new ElementCreator();
	CommandReceiver commandReceiver = new CommandReceiver(commandInvoker, elementCreator);

	static Selector selector;
	static SocketChannel connectionClient;

	public static ArrayList<String> level_list = new ArrayList<>();

	public static ArrayList<MusicBand> data = new ArrayList<>();



	public ClientController(String hostname, String port) {
		ClientController.hostname = hostname;
		ClientController.port = Integer.parseInt(port);
	}

	public void run() {
		if (hostname == null || port == -1) {
			System.out.println("Класс не инициализирован");
			throw new RuntimeException("Не инициализирован hostname и port");
		} else {
			while (true) {
				try {
					selector = Selector.open();
					connectionClient = SocketChannel.open();
					connectionClient.connect(new InetSocketAddress("localhost", port));
					connectionClient.configureBlocking(false);
					//connectionClient.register(selector, SelectionKey.OP_CONNECT);
					connectionClient.register(selector, SelectionKey.OP_WRITE);

					commandInvoker.register("help", new Help(commandReceiver));
					commandInvoker.register("add", new Add(commandReceiver));
					commandInvoker.register("info", new Info(commandReceiver));
					commandInvoker.register("show", new Show(commandReceiver));
					commandInvoker.register("update", new Update(commandReceiver));
					commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
					commandInvoker.register("remove_by_description", new RemoveByDescription(commandReceiver));
					commandInvoker.register("filter_contains_name", new FilterContainsName(commandReceiver));
					commandInvoker.register("reorder", new Reorder(commandReceiver));
					commandInvoker.register("clear", new Clear(commandReceiver));
					commandInvoker.register("exit", new Exit(commandReceiver));
					commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));
					commandInvoker.register("remove_lower", new RemoveLower(commandReceiver));
					commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));

					break;
				} catch (ConnectException e) {
					//System.out.println("Невозможно подключиться к данному хосту или порту");
					//System.out.println("Возможно сервер временно не доступен или указан неправильный адрес");
					System.out.println("В данный момент сервер не доступен, повторная попытка: " + reconect_schetchick);
					if (reconect_schetchick > 20) {
						System.exit(0);
					}
					try {
						selector = Selector.open();
						connectionClient = SocketChannel.open();
						connectionClient.configureBlocking(false);
						connectionClient.connect(new InetSocketAddress(hostname, port));
						connectionClient.register(selector, SelectionKey.OP_WRITE);
					} catch (IOException ignored) {
					}
					reconect_schetchick++;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Message getSocketObject() throws IOException, ClassNotFoundException {
		ByteBuffer data = ByteBuffer.allocate(BUFF_SIZE);
		client.read(data);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.array());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		return (Message) objectInputStream.readObject();
	}

	public static void sendSocketObject(Message message) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(message);
		objectOutputStream.flush();
		client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
	}

	public static String readThread() throws IOException, ClassNotFoundException {
		selector.select();
		for (SelectionKey key : selector.selectedKeys()) {
			//iterator.remove();
			if (key.isValid()) {
				client = (SocketChannel) key.channel();
				if (client != null) {
					try {
						if ((key.interestOps() & SelectionKey.OP_READ) != 0) {
							Message message = getSocketObject();
							data = message.getArraylist();
							System.out.println(message.getString());
							key.interestOps(SelectionKey.OP_WRITE);
							client.register(selector, SelectionKey.OP_WRITE);
							return message.getString();
						}

					} catch (ConnectException e) {
						System.out.println("В данный момент сервер не доступен, повторная попытка: " + reconect_schetchick);
						if (reconect_schetchick > 20) {
							System.exit(0);
						}
						selector = Selector.open();
						connectionClient = SocketChannel.open();
						connectionClient.configureBlocking(false);
						connectionClient.connect(new InetSocketAddress(hostname, port));
						connectionClient.register(selector, SelectionKey.OP_WRITE);
						reconect_schetchick++;
					} catch (IOException ex) {
						System.out.println("Сервер закрыл соединение");
						System.out.println("Повторное подлючение");
						//client.register(selector, SelectionKey.OP_CONNECT);
						//connectionClient.register(selector, SelectionKey.OP_CONNECT);
						selector = Selector.open();
						connectionClient = SocketChannel.open();
						connectionClient.connect(new InetSocketAddress("localhost", port));
						connectionClient.configureBlocking(false);
						//connectionClient.register(selector, SelectionKey.OP_CONNECT);
						connectionClient.register(selector, SelectionKey.OP_WRITE);
					} catch (NoSuchElementException e) {
						System.out.println("Завершение работы.");
						client.close();
						e.printStackTrace();

						System.exit(0);
					}
				}
			}
		}
		return "";
	}

	public static boolean writeThread(Message message) throws IOException {
		selector.select();
		for (SelectionKey key : selector.selectedKeys()) {
			//iterator.remove();
			if (key.isValid()) {
				client = (SocketChannel) key.channel();
				if (client != null) {
					try {
						if ((key.interestOps() & SelectionKey.OP_WRITE) != 0) {
							if (message != null) {
								message.setUserPass(ClientController.name, ClientController.pass);
								sendSocketObject(message);
								key.interestOps(SelectionKey.OP_READ);
								client.register(selector, SelectionKey.OP_READ);
							}
							return true;
						}
					} catch (ConnectException e) {
						System.out.println("В данный момент сервер не доступен, повторная попытка: " + reconect_schetchick);
						if (reconect_schetchick > 20) {
							System.exit(0);
						}
						selector = Selector.open();
						connectionClient = SocketChannel.open();
						connectionClient.configureBlocking(false);
						connectionClient.connect(new InetSocketAddress(hostname, port));
						connectionClient.register(selector, SelectionKey.OP_WRITE);
						reconect_schetchick++;
					} catch (IOException ex) {
						System.out.println("Сервер закрыл соединение");
						System.out.println("Повторное подлючение");
						//client.register(selector, SelectionKey.OP_CONNECT);
						//connectionClient.register(selector, SelectionKey.OP_CONNECT);
						selector = Selector.open();
						connectionClient = SocketChannel.open();
						connectionClient.connect(new InetSocketAddress("localhost", port));
						connectionClient.configureBlocking(false);
						//connectionClient.register(selector, SelectionKey.OP_CONNECT);
						connectionClient.register(selector, SelectionKey.OP_WRITE);
					} catch (NoSuchElementException e) {
						System.out.println("Завершение работы.");
						client.close();
						e.printStackTrace();

						System.exit(0);
					}
				}
			}
		}
		return false;
	}

	private static boolean connectThread(SelectionKey key, Selector selector) throws IOException, InterruptedException {
		if ((key.interestOps() & SelectionKey.OP_CONNECT) != 0) {
			try {
				if (client.finishConnect()) {
					key.interestOps(SelectionKey.OP_WRITE);
					client.register(selector, SelectionKey.OP_WRITE);
					System.out.println("Введите help");
				} else {
					System.out.println("В данный момент сервер не доступен, повторная попытка: " + reconect_schetchick);
					if (reconect_schetchick > 20) {
						System.exit(0);
					}
					selector = Selector.open();
					connectionClient = SocketChannel.open();
					connectionClient.configureBlocking(false);
					connectionClient.connect(new InetSocketAddress(hostname, port));
					connectionClient.register(selector, SelectionKey.OP_WRITE);
					reconect_schetchick++;
					sleep(1000);
				}
				return true;
			} catch (IOException | InterruptedException e) {
				System.out.println("В данный момент сервер не доступен, повторная попытка: " + reconect_schetchick);
				if (reconect_schetchick > 20) {
					System.exit(0);
				}
				selector = Selector.open();
				connectionClient = SocketChannel.open();
				connectionClient.configureBlocking(false);
				connectionClient.connect(new InetSocketAddress(hostname, port));
				connectionClient.register(selector, SelectionKey.OP_WRITE);
				reconect_schetchick++;
				sleep(1000);
				return true;
			}
		}
		return false;
	}

	public static String fastWrite(Message message) {
		String result = "";
		try {
			if (writeThread(message)) {
				result = readThread();
			} else {
				result = "Сообщение не отправлено. Попробуйте позже.";
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}