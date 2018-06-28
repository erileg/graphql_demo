package de.engram.graphql_demo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class EntityNotFoundException extends RuntimeException implements GraphQLError {
	private Map<String, Object> extensions = new HashMap<>();

	public EntityNotFoundException(String message, Long invalidEntityId) {
		super(message);
		extensions.put("invalidEntityId", invalidEntityId);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public Map<String, Object> getExtensions() {
		return extensions;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}
}