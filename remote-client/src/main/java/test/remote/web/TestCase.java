package test.remote.web;

import com.vaadin.ui.*;
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
    private TextField infoResult;
    private ProgressBar progressBar;
    private List<Long> timesResult;

    public TestCase(String name, Executor toexec) {
        executor = toexec;
        infoResult = new TextField("Test time [ms]");
        progressBar = new ProgressBar();


        Label infoTest = new Label(name);
        Button buttonExec = new Button("Run case");
        buttonExec.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    timesResult = new ArrayList<Long>();
                    //if (timesResult == null) {
                    //}
                    //Initial connect
                    executor.doExecute();
                    Thread.sleep(500);
                    for (int i = 0; i < 14; i++) {
                        long t = System.currentTimeMillis();
                        executor.doExecute();
                        long td = System.currentTimeMillis() - t;
                        addTimeResult(td);
                        Thread.sleep(100);
                    }
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
        //addComponent(new TextField("Network size [MB]", ""));

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
        progressBar.setValue(time / 1000f);
        infoResult.setCaption((average ? "Average time [ms]" : "Test time [ms]"));
        infoResult.setValue(time.toString());
    }

    public static interface Executor {

        public void doExecute() throws Exception;

    }
}
