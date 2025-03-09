/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Asus
 */
public class StringUtils {
    public static boolean checkEmpty(String str) {
        boolean isEmpty = str.trim().equals("");
        return isEmpty;
    }
}
