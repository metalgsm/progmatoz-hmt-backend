package com.prog.matoz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Elastic {
	
	private Settings settings;
	private Mappings mappings;
	
	public class Settings {
		public int number_of_shards;
	}

	public class Raw {
		public String type;
	}

	public class Fields {
		public Raw raw;
	}

	public class Client {
		public String type;
		public Fields fields;
	}

	public class Product {
		public String type;
		public Fields fields;
	}

	public class Service {
		public String type;
		public Fields fields;
	}

	public class Transaction {
		public String type;
		public Fields fields;
	}

	public class UUID {
		public String type;
	}

	public class Key {
		public String type;
	}

	public class RequestHeaders {
		public String type;
	}

	public class RequestUri {
		public String type;
	}

	public class RequestBody {
		public String type;
	}

	public class ResponseHeaders {
		public String type;
	}

	public class ResponseUri {
		public String type;
	}

	public class ResponseStatus {
		public String type;
	}

	public class ResponseBody {
		public String type;
	}

	public class Properties {
		public Client client;
		public Product product;
		public Service service;
		public Transaction transaction;
		public UUID UUID;
		public Key key;
		public RequestHeaders requestHeaders;
		public RequestUri requestUri;
		public RequestBody requestBody;
		public ResponseHeaders responseHeaders;
		public ResponseUri responseUri;
		public ResponseStatus responseStatus;
		public ResponseBody responseBody;
	}

	public class Mappings {
		public Properties properties;
	}

	public class Root {
		public Settings settings;
		public Mappings mappings;
	}
}
