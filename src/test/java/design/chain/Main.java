package design.chain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2020/7/10 18:03
 * 责任链模式 封装变化
 */
public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "request";
        Response response = new Response();
        response.str = "response";
        FilterChain chain = new FilterChain().addFilter(new MsgFilter()).addFilter(new HtmlFilter());
        chain.filter(request, response, chain);
    }
}

@Data
@ToString
class Msg {
    String name;
    String msg;
}

class Request {
    String str;
}

class Response {
    String str;
}

interface Filter {
    boolean filter(Request request, Response response, FilterChain chain);
}

class MsgFilter implements Filter {

    @Override
    public boolean filter(Request request, Response response, FilterChain chain) {
        request.str.replace("1", "2");
        System.out.println("test MSG-----");
        chain.filter(request, response, chain);
        response.str.replace("3", "4");
        System.out.println("test MSG");
        return true;
    }
}

class HtmlFilter implements Filter {

    @Override
    public boolean filter(Request request, Response response, FilterChain chain) {
        request.str.replace("1", "2");
        System.out.println("test HTML------");
        chain.filter(request, response, chain);
        response.str.replace("3", "4");
        System.out.println("test HTML");
        return true;
    }
}

@Data
class FilterChain implements Filter {
    public List<Filter> filter = new ArrayList<>();
    int index = 0;

    public FilterChain addFilter(Filter f) {
        filter.add(f);
        return this;
    }

    @Override
    public boolean filter(Request request, Response response, FilterChain chain) {
        if (index == filter.size()) return false;
        Filter f = chain.getFilter().get(index);
        index++;
        System.out.println(index);
        return f.filter(request, response, chain);
    }
}
