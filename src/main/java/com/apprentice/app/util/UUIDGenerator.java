package com.apprentice.app.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDGenerator {
    public static String createUUID() {
        UUID uuidV1 = Generators.timeBasedGenerator().generate();
        String[] uuidV1Parts = uuidV1.toString().split("-");
        return uuidV1Parts[2] + uuidV1Parts[1] + uuidV1Parts[0] + uuidV1Parts[3] + uuidV1Parts[4];
    }
}
