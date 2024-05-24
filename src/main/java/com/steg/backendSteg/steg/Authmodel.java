package com.steg.backendSteg.steg;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

public class Authmodel {

        private String email;
        private String password;
}
