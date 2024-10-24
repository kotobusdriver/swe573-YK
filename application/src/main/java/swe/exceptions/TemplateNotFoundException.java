package swe.exceptions;

public class TemplateNotFoundException extends RuntimeException {
  public TemplateNotFoundException(String templateId) {
    super("Template is not found. TemplateId: %s".formatted(templateId));
  }
}
