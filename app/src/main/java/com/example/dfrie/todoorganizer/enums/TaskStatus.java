package com.example.dfrie.todoorganizer.enums;

/**
 * Created by dfrie on 2/5/2017.
 */

public enum TaskStatus {
    TODO(0, "To Do"),
    DONE(1, "Done"),
    ABORTED(2, "Aborted");

    private int value;
    private String name;

    private TaskStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static TaskStatus getEnumFor(int code) {
        switch (code) {
            case 1:
                return DONE;
            case 2:
                return ABORTED;
        }
        return TODO;
    }

    public static TaskStatus getEnumFor(String str) {
        if (str.toUpperCase().equals(DONE.getName().toUpperCase())) {
            return DONE;
        }
        if (str.toUpperCase().equals(ABORTED.getName().toUpperCase())) {
            return ABORTED;
        }
        return TODO;
    }
}
