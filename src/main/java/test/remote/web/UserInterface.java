package test.remote.web;

import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import test.remote.AppControler;


/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class UserInterface extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Remoting Test");
        HorizontalLayout cases = new HorizontalLayout();

        cases.addComponent(new TestCase("Simple Invoker", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicClient().transferPull(false);
            }
        }));
        cases.addComponent(new TestCase("Simple Jax-WS", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicWebClient().transferPull(false);
            }
        }));
        cases.addComponent(new TestCase("Complex Invoker", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicClient().transferPull(true);
            }
        }));
        cases.addComponent(new TestCase("Complex Jax-WS", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicWebClient().transferPull(true);
            }
        }));


        final HorizontalLayout devel = new HorizontalLayout();
        Button button = new Button("happy developer");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Image image = new Image(null, new ClassResource("/freeski_small.jpg"));
                devel.addComponent(image);
            }
        });
        devel.addComponent(button);
        devel.setMargin(true);

        VerticalLayout root = new VerticalLayout();
        root.addComponent(cases);
        root.addComponent(devel);
        root.setMargin(true);
        setContent(root);
    }

    public AppControler appControler() {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        return context.getBean(AppControler.class);
    }
}
