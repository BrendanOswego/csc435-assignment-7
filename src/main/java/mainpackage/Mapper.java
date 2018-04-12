package mainpackage;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
  
  private static final ObjectMapper mapper = new ObjectMapper();
  private static Mapper instance = null;

  private Mapper() {}

  public static Mapper instance() {
    if (instance == null)
      instance = new Mapper();
    return instance;
  }

  public ObjectMapper mapper() {
    return mapper;
  }
}