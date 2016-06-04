package email;

import email.models.Email;

public interface EmailServer {
    void send(Email email);
}
