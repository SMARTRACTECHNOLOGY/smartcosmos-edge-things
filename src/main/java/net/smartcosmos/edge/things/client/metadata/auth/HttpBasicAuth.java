package net.smartcosmos.edge.things.client.metadata.auth;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.migcomponents.migbase64.Base64;

import net.smartcosmos.edge.things.client.metadata.Pair;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class HttpBasicAuth implements Authentication {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
        if (username == null && password == null) {
            return;
        }
        String str = (username == null ? "" : username) + ":" + (password == null ? "" : password);
        try {
            headerParams.put("Authorization", "Basic " + Base64.encodeToString(str.getBytes("UTF-8"), false));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
