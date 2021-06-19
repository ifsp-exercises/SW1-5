package Entities;

public class ResponseResult {
  public boolean success;
  public String message;
  public Object data;

  public ResponseResult(boolean success, String message, Object data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }
}
