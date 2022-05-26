package cz.nss.onegram.user.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MessageKafka {

    private String message;

    private LocalDate sentAtDate = LocalDate.now();

    private LocalTime sentAtTime = LocalTime.now();

    private boolean hasRead = false;

    private String receiver;

    private String sender;
}
