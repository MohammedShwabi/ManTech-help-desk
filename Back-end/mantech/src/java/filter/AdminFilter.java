/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entities.Employees;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // Get the session without creating a new one

        // Check if the user is logged in
        if (session != null && session.getAttribute("loggedInEmployee") != null) {

            Employees loggedInEmployee = (Employees) session.getAttribute("loggedInEmployee");
            
            // Check if the user is an admin
            if (loggedInEmployee.getDepId().getId() == 7) {
                // Admin has access to all folders, so continue the request
                chain.doFilter(request, response);
                return;
            }
        }
        // Redirect to an unauthorized page (customize the page as needed)
        // request.getRequestDispatcher("/unauthorized.xhtml").forward(request, response);

        // User is not logged in, redirect to the login page
        request.getRequestDispatcher("/login.xhtml").forward(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
