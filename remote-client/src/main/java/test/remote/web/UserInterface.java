package test.remote.web;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import test.remote.AppControler;
import test.remote.PublicWebClient;
import test.remote.api.PublicService;

import java.util.Arrays;


/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class UserInterface extends UI {

    void doHappy() {
        PublicWebClient webClient = appControler().getPublicWebClient();
        webClient.getClass();
    }

    CheckBox optionBinary;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Remoting Test");

        VerticalLayout options = new VerticalLayout();
        HorizontalLayout cases = new HorizontalLayout();

        options.addComponent(new ComboBox("System enviroment ",Arrays.asList(new String[]{
                "os Linux | jdk 6 | lo",
                "os Linux | jdk 7 | lo",
                "os Linux | jdk 6 | lan",
                "os Linux | jdk 7 | lan",
                "os Windows | jdk 6 | lo",
                "os Windows | jdk 7 | lo",
                "os Windows | jdk 6 | lan",
                "os Windows | jdk 7 | lan"
        })));
        optionBinary = new CheckBox("Binary payload",true);
        options.addComponent(optionBinary);

        cases.addComponent(new TestCase("Invoker", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicInvokerClient().transferPull(optionBinary.getValue());
            }
        }));
        cases.addComponent(new TestCase("Jax-WS Binary", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                PublicService jaxwsBinary = appControler().getPublicJaxwsBinary();
                if (jaxwsBinary != null)
                    jaxwsBinary.transferPull(optionBinary.getValue());
            }
        }));
        cases.addComponent(new TestCase("Jax-WS Normal", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicJaxwsClient().transferPull(optionBinary.getValue());
            }
        }));
        cases.addComponent(new TestCase("Jax-WS Mapping WO", new TestCase.Executor() {
            @Override
            public void doExecute() throws Exception {
                appControler().getPublicWebClient().transferPull(optionBinary.getValue());
            }
        }));


        final HorizontalLayout devel = new HorizontalLayout();
        Button button = new Button("happy developer");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                /*Image image = new Image(null, new ClassResource("/freeski.jpg"));
                image.setWidth(600f,Unit.PIXELS);
                image.setHeight(400f,Unit.PIXELS);
                devel.addComponent(image);*/
                doHappy();
            }
        });
        devel.addComponent(button);

        VerticalLayout root = new VerticalLayout();
        options.setMargin(true);
        devel.setMargin(true);
        root.setMargin(true);
        root.addComponent(options);
        root.addComponent(cases);
        root.addComponent(devel);
        setContent(root);
    }

    public AppControler appControler() {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        return context.getBean(AppControler.class);
    }
}
