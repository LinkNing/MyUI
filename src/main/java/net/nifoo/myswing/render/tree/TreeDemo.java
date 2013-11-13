package net.nifoo.myswing.render.tree;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.nifoo.myswing.Console;

public class TreeDemo extends JPanel {

	private static final long serialVersionUID = 1L;

	private static String ADD_COMMAND = "add";
	private static String REMOVE_COMMAND = "remove";
	private static String CLEAR_COMMAND = "clear";
	private int newNodeSuffix = 1;

	JTree tree;
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode top;
	JButton btnAdd = new JButton("Add");
	JButton btnDel = new JButton("Remove");
	JButton btnClear = new JButton("Clear");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public TreeDemo() {
		setLayout(new BorderLayout());

		top = new DefaultMutableTreeNode("The Java Series");
		createNodes(top);

		treeModel = new DefaultTreeModel(top);
		treeModel.addTreeModelListener(new MyTreeModelListener());

		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//tree.setRootVisible(true);
		tree.setShowsRootHandles(true);

		tree.setCellRenderer(new MyRenderer(createImageIcon("resources/book.png")));

		//Listen for when the selection changes.
		tree.addTreeSelectionListener(new MyTreeSelectionListener());

		JScrollPane treeView = new JScrollPane(tree);

		add(treeView, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel(new GridLayout(0, 3));
		ActionListener actionListener = new MyActionListener();
		btnAdd.setActionCommand(ADD_COMMAND);
		btnAdd.addActionListener(actionListener);
		buttonPane.add(btnAdd);

		btnDel.setActionCommand(REMOVE_COMMAND);
		btnDel.addActionListener(actionListener);
		buttonPane.add(btnDel);

		btnClear.setActionCommand(CLEAR_COMMAND);
		btnClear.addActionListener(actionListener);
		buttonPane.add(btnClear);

		add(buttonPane, BorderLayout.SOUTH);
	}

	public void displayURL(String url) {
		System.out.println(url);
	}

	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;

		category = new DefaultMutableTreeNode("Books for Java Programmers");
		top.add(category);

		//original Tutorial
		book = new DefaultMutableTreeNode(new BookInfo("The Java Tutorial: A Short Course on the Basics",
				"tutorial.html"));
		category.add(book);

		//Tutorial Continued
		book = new DefaultMutableTreeNode(new BookInfo("The Java Tutorial Continued: The Rest of the JDK",
				"tutorialcont.html"));
		category.add(book);

		//Swing Tutorial
		book = new DefaultMutableTreeNode(new BookInfo("The Swing Tutorial: A Guide to Constructing GUIs",
				"swingtutorial.html"));
		category.add(book);

		//...add more books for programmers...

		category = new DefaultMutableTreeNode("Books for Java Implementers");
		top.add(category);

		//VM
		book = new DefaultMutableTreeNode(new BookInfo("The Java Virtual Machine Specification", "vm.html"));
		category.add(book);

		//Language Spec
		book = new DefaultMutableTreeNode(new BookInfo("The Java Language Specification", "jls.html"));
		category.add(book);
	}

	/** Add child to the currently selected node. */
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = top;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = top;
		}

		//It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		//Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	/** Remove all nodes except the root node. */
	public void clear() {
		top.removeAllChildren();
		treeModel.reload();
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}

		// Either there was no selection, or the root was selected.
		toolkit.beep();
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TreeIconDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	class MyTreeSelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			//Returns the last path element of the selection.
			//This method is useful only when the selection model allows a single selection.
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

			if (node == null)
				//Nothing is selected.     
				return;

			Object nodeInfo = node.getUserObject();
			if (node.isLeaf()) {
				BookInfo book = (BookInfo) nodeInfo;
				displayURL(book.getUrl());
			} else {
				displayURL("help.html");
			}
		}
	}

	class MyRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;

		Icon tutorialIcon;

		public MyRenderer(Icon icon) {
			tutorialIcon = icon;
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if (leaf && isTutorialBook(value)) {
				setIcon(tutorialIcon);
				setToolTipText("This book is in the Tutorial series.");
			} else {
				setToolTipText(null);
			}

			return this;
		}

		protected boolean isTutorialBook(Object value) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

			if (!(node.getUserObject() instanceof BookInfo))
				return false;

			BookInfo nodeInfo = (BookInfo) (node.getUserObject());
			String title = nodeInfo.getName();
			if (title.indexOf("Tutorial") >= 0) {
				return true;
			}

			return false;
		}
	}

	class MyTreeModelListener implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

			/*
			 * If the event lists children, then the changed
			 * node is the child of the node we've already
			 * gotten.  Otherwise, the changed node and the
			 * specified node are the same.
			 */

			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode) (node.getChildAt(index));

			System.out.println("The user has finished editing the node.");
			System.out.println("New value: " + node.getUserObject());
		}

		public void treeNodesInserted(TreeModelEvent e) {
		}

		public void treeNodesRemoved(TreeModelEvent e) {
		}

		public void treeStructureChanged(TreeModelEvent e) {
		}
	}

	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (ADD_COMMAND.equals(command)) {
				//Add button clicked
				addObject(new BookInfo("Book " + newNodeSuffix++, "not exist"));
			} else if (REMOVE_COMMAND.equals(command)) {
				//Remove button clicked
				removeCurrentNode();
			} else if (CLEAR_COMMAND.equals(command)) {
				//Clear button clicked.
				clear();
			}
		}
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Console.run(new TreeDemo(), 800, 600);
			}
		});

	}

}
