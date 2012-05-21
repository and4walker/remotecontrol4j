package org.remotecontrol4j.server.runtime;

import java.io.IOException;

public class TestProcess
{

	public static void main(String... args) throws IOException{
		ProcessBuilder pb = new ProcessBuilder("ipconfig");
		pb.command("");
		pb.start();
		
		System.out.println("90909");
	}
}
