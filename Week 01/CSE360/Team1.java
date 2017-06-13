package CSE360;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class Team1 extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int TEAM_SIZE = 4;
	private static String[] memberNames = { "Daniel Ene Neagu", "Manuel Bravo",
			"Rozhin Azima", "Shrinivas Bhat" };
	private static String[] memberEmailIds = { "deneneag@asu.edu",
			"mbravo6@asu.edu", "Razima@asu.edu", "sbhat10@asu.edu" };

	private JLabel teamName;
	private JLabel nameabel;
	private ArrayList<JLabel> name = new ArrayList<JLabel>();

	public Team1() {
		initComponents();
	}

	private void initComponents() {

		initTeamTags();
		initTeamDetails();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(name.get(3))
										.addComponent(name.get(2))
										.addComponent(name.get(1))
										.addComponent(name.get(0))
										.addComponent(nameabel)
										.addComponent(teamName))
						.addContainerGap(288, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(teamName)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(nameabel)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(name.get(0))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(name.get(1))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(name.get(2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(name.get(3))
								.addGap(0, 186, Short.MAX_VALUE)));
	}

	private void initTeamDetails() {
		for (int i = 0; i < TEAM_SIZE; i++) {
			name.add(new JLabel());
			name.get(i).setText(memberNames[i] + "(" + memberEmailIds[i] + ")");
		}
	}

	private void initTeamTags() {
		teamName = new JLabel();
		nameabel = new JLabel();

		teamName.setFont(new Font("Tahoma", 3, 11));
		teamName.setForeground(new Color(153, 0, 0));
		teamName.setText("Team1");

		nameabel.setFont(new Font("Tahoma", 1, 11));
		nameabel.setText("Name");
	}
}
