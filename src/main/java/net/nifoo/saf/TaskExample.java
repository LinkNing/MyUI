package net.nifoo.saf;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.Task;

public class TaskExample extends SingleFrameApplication {

	private final JPanel mainPanel = new JPanel();
	private final JButton btnStart = new JButton("Start");
	private final JButton btnStop = new JButton("Stop");
	private final JProgressBar progressBar = new JProgressBar();
	private final JTextField txtShowTime = new JTextField();;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void startup() {

		mainPanel.setLayout(null);

		btnStart.setBounds(99, 52, 93, 23);
		mainPanel.add(btnStart);
		btnStart.setAction(getAction("start"));

		btnStop.setBounds(237, 52, 93, 23);
		mainPanel.add(btnStop);
		btnStop.setAction(getAction("stop"));

		progressBar.setBounds(99, 132, 231, 14);
		mainPanel.add(progressBar);

		txtShowTime.setBounds(99, 184, 93, 21);
		mainPanel.add(txtShowTime);
		txtShowTime.setColumns(10);

		JButton btnShowTime = new JButton("Show Time");
		btnShowTime.setBounds(215, 183, 115, 23);
		mainPanel.add(btnShowTime);

		show(mainPanel);
	}

	private javax.swing.Action getAction(String actionName) {
		ActionMap map = getContext().getActionMap(this);
		return map.get(actionName);
	}

	private Task doNothingTask = null;

	@Action
	public Task start() {
		//		stop(); // stop the pending DoListFiles task (if any)
		//		setStopEnabled(true);
		doNothingTask = new DoNothingTask(this);
		return doNothingTask;
	}

	@Action
	public void stop() {
		if ((doNothingTask != null) && !doNothingTask.isCancelled()) {
			if (doNothingTask.cancel(true)) {
				doNothingTask = null;
			}
		}
	}

	class DoNothingTask extends Task<Void, Integer> {
		public DoNothingTask(Application application) {
			super(application);

			// monitor progress
			addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("progress".equals(evt.getPropertyName())) {
						Integer value = (Integer) evt.getNewValue();
						progressBar.setValue(value);
					}
				}
			});
		}

		@Override
		protected Void doInBackground() throws InterruptedException {
			for (int i = 0; i < 100; i++) {
				setMessage("Working [" + i + "]");
				Thread.sleep(50);
				setProgress(i, 0, 99);
			}
			Thread.sleep(150L);
			return null;
		}

		@Override
		protected void finished() {
			System.out.println("Process is finished!");
			setMessage(isCancelled() ? "Canceled." : "Done.");
		}

		@Override
		protected void succeeded(Void value) {
			System.out.println("Process is success!");
		}
		
		@Override
		protected void cancelled() {
			System.out.println("Process is cancelled!");
		}
		
		@Override
		protected void failed(Throwable e) {
			System.out.println("Process is brocked!");
		}

	}

	public static void main(String[] args) {
		launch(TaskExample.class, args);
	}
}
