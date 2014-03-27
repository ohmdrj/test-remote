package test.remote.web;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TestCase
 *
 * @author Ondrej Burianek
 */
public class TestCase extends VerticalLayout {

    private Executor executor;
    private com.vaadin.ui.Label infoResult;
    private ProgressBar progressBar;
    private List<Long> timesResult = new ArrayList<Long>();

    public TestCase(String name, Executor toexec) {
        executor = toexec;
        infoResult = new Label("n/a");
        progressBar = new ProgressBar();


        Label infoTest = new Label(name);
        Button buttonExec = new Button("Run case");
        buttonExec.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    long t = System.currentTimeMillis();
                    executor.doExecute();
                    long td = System.currentTimeMillis() - t;
                    addTimeResult(td);
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

    protected void addTimeResult(Long time) {
        timesResult.add(time);
        boolean average = false;
        if (timesResult.size() > 5) {
            Collections.sort(timesResult);
            time = 0l;
            List<Long> times = timesResult.subList(2, timesResult.size() - 2);
            System.out.println(times.size() + " average from " + timesResult.size());
            for (Long t : times) {
                time += t;
            }
            time = Math.round(time / (double) times.size());
            average = true;
        }
        infoResult.setValue((average?"Average":"Test")+" in " + time + "ms");
    }

    public static interface Executor {

        public void doExecute() throws Exception;

    }
}
