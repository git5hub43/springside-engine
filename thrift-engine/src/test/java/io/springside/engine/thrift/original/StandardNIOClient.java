package io.springside.engine.thrift.original;

import io.springside.engine.thrift.tutorial.Calculator;

import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class StandardNIOClient {

	public static void main(String[] args) throws IOException {

		try {
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			Calculator.Client client = new Calculator.Client(protocol);

			perform(client);
			transport.close();

		} catch (TException x) {
			x.printStackTrace();
		}
	}

	private static void perform(Calculator.Client client) throws TException {
		for (int i = 1; i <= 10; i++) {
			int sum = client.add(i, i);
			System.out.println(i + "+" + i + "=" + sum);
		}
	}
}
