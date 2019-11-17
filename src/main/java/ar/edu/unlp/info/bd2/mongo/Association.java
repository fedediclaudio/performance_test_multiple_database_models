package ar.edu.unlp.info.bd2.mongo;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Association {

  public Association(ObjectId source, ObjectId destination) {
    this.source = source;
    this.destination = destination;
  }

  public Association() { }

  @BsonId private ObjectId id;

  @BsonProperty
  private ObjectId source;

  @BsonProperty
  private ObjectId destination;

  public ObjectId getSource() {
    return source;
  }

  public void setSource(ObjectId source) {
    this.source = source;
  }

  public ObjectId getDestination() {
    return destination;
  }

  public void setDestination(ObjectId destination) {
    this.destination = destination;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public Document asDocument() {
    return new Document("source", this.source).append("destination", this.destination);
  }
}
