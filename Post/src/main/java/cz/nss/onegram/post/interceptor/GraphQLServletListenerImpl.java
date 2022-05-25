package cz.nss.onegram.post.interceptor;

import cz.nss.onegram.post.util.LoggingUtil;
import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class GraphQLServletListenerImpl implements GraphQLServletListener {

    private final LoggingUtil loggingUtil;

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new RequestCallback() {
            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                loggingUtil.logRequest(request);
            }
        };
    }
}