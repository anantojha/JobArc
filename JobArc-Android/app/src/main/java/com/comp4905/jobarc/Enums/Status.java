package com.comp4905.jobarc.Enums;

public enum Status {
    CLOSED {
        @Override
        public String toString() {
            return "closed";
        }
    },
    OPEN {
        @Override
        public String toString() {
            return "open";
        }
    }
}
