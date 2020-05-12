package com.student.security;

/**
 *
 * @author simiyu
 */
public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    //public static final long EXPIRATION_TIME = 60_000; // 1 minute
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}