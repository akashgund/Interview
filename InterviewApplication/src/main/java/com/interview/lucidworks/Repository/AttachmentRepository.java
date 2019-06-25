package com.interview.lucidworks.Repository;
import com.interview.lucidworks.Entity.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachment, String> {
}
