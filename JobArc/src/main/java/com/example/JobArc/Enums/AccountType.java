package com.example.JobArc.Enums;

public enum AccountType {
    employer {
        @Override
        public String toString(){
            return "employer";
        }
    },
    jobseeker {
        @Override
        public String toString(){
            return "jobseeker";
        }
    }
}
