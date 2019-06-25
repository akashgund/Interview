package com.interview.lucidworks.Util;

import java.util.UUID;

public class UUIDGenerator {



        public static String getUUID() {

            UUID uuid = UUID.randomUUID();
            String x = uuid.toString();
            return x;
        }


}
