package com.msg.proj.repository;

public class RepoFactory {
    private RepoFactory() {
    }

    public static IRepo createRepo() {
        return new GenericRepo();
    }
}
