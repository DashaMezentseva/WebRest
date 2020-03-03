package com.nixsolutions.web;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.nixsolutions.domain.User;
import com.nixsolutions.web.LoginController;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    private static final String roleAdmin = "ROLE_ADMIN";
    private static final String roleUser = "ROLE_USER";

    public LoginControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));
    }

//    @Test
//    public void testDispatchAdmin() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.isUserInRole(roleAdmin)).thenReturn(true);
//        loginController.dispatch(request);
//        verify(request, times(1)).isUserInRole(roleAdmin);
//        verify(request, never()).isUserInRole(roleUser);
//    }
//
//    @Test
//    public void testDispatchUser() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.isUserInRole(roleUser)).thenReturn(true);
//        loginController.dispatch(request);
//        verify(request, times(1)).isUserInRole(roleAdmin);
//        verify(request, times(1)).isUserInRole(roleUser);
//    }
//
//    @Test
//    public void testDispatchIndex() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.isUserInRole(anyString())).thenReturn(false);
//        loginController.dispatch(request);
//        verify(request, times(2)).isUserInRole(anyString());
//    }


    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }



}
