package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void showMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String messageResponse = "";
            String alert = "";
            String message = request.getParameter("message");
            if (message.equals("insert_success")) {
                messageResponse = "Insert successfully!";
                alert = "success";
            } else if (message.equals("update_success")) {
                messageResponse = "Update successfully!";
                alert = "success";
            } else if (message.equals("delete_success")) {
                messageResponse = "Delete successfully!";
                alert = "success";
            } else if (message.equals("error_system")) {
                messageResponse = "Error system!";
                alert = "danger";
            }
            request.setAttribute("messageResponse", messageResponse);
            request.setAttribute("alert", alert);
        }
    }
}
