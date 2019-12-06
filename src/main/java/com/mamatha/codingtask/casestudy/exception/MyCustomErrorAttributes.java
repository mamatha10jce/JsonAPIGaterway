package com.mamatha.codingtask.casestudy.exception;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {
 
    @Override
    public Map<String, Object> getErrorAttributes(
      WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = 
          super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("locale", webRequest.getLocale()
            .toString());
        errorAttributes.remove("error");
        return errorAttributes;
    }
}