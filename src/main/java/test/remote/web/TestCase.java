package test.remote.web;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * TestCase
 *
 * @author Ondrej Burianek
 */
public class TestCase extends VerticalLayout {

    private Executor executor;
    private com.vaadin.ui.Label infoResult;
    private ProgressBar progressBar;
    public TestCase(String name,Executor toexec) {
        executor= toexec;
        infoResult = new Label("n/a");
        progressBar=new ProgressBar();


        Label infoTest= new Label(name);
        Button buttonExec = new Button("Run case");
        buttonExec.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    long t = System.currentTimeMillis();
                    executor.doExecute();
                    long td = System.currentTimeMillis() - t;

                    infoResult.setValue("Invoked in " + td + "ms");
                    progressBar.setValue(td / 1000f);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    infoResult.setValue("Exception: " + ex.getMessage());
                }
            }
        });
        addComponent(infoTest);
        addComponent(buttonExec);
        addComponent(progressBar);
        addComponent(infoResult);

        infoTest.addStyleName(Reindeer.LABEL_H2);
        setSizeUndefined();
        setMargin(true);
    }

    public static interface Executor {

        public void doExecute() throws Exception;

    }
}
