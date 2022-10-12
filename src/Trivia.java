import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Trivia implements ActionListener {

    String[] questions = {
            "What is the largest continent on the planet?",
            "Which country has the world’s longest coastline?",
            "Which is the World’s Largest Ocean?",
            "What is the hardest rock on earth?",
            "Which is considered a semi-planet?",
            "What kind of currency do you use with villagers in Minecraft?",
            "Which song is by Taylor Swift?",
            "What band was Harry Styles in before his solo career?",
            "How many weeks are in a year?",
            "When is Mother's day?",
            "What year was the Declaration of Independence signed?"
    };

    String[][] options = {
            {"North America", "Russia", "Asia", "Europe"},
            {"Canada", "United Sates", "Taiwan", "Mexico"},
            {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},
            {"sedimentary", "igneous", "metamorphic", "diamond"},
            {"Venus", "Mars", "Pluto", "Septum"},
            {"diamond", "bread", "emerald", "gold"},
            {"Trouble", "Cruel Summer", "Lover", "All of the above"},
            {"One Direction", "5SOS", "Maroon 5", "Chase Atlantic"},
            {"51 weeks", "53 weeks", "66 weeks", "52 weeks"},
            {"May 22nd", "March 12th", "April 28th", "May 14th"},
            {"1760", "1776", "1778", "1755"}
    };

    char[] answers = {
            'C',
            'A',
            'D',
            'D',
            'C',
            'C',
            'D',
            'A',
            'D',
            'D',
            'B'
    };

    char guess;
    char answer;
    int index;
    int correctGuesses = 0;
    int totalQuestions = questions.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerLabelA = new JLabel();
    JLabel answerLabelB = new JLabel();
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            secondsLeft.setText(String.valueOf(seconds));
            if(seconds == 0) {
                displayAnswer();
            }
        }
    });

    public Trivia() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.getContentPane().setBackground(new Color(220, 208, 255));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(10,10,730, 60);
        textField.setBackground(new Color(169, 92, 104));
        textField.setForeground(new Color(255,255,255));
        textField.setFont(new Font("Dialog", Font.BOLD, 40));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(10,80,730, 80);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(169, 92, 104));
        textArea.setForeground(new Color(255,255,255));
        textArea.setFont(new Font("Serif", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(10,200,100,100);
        buttonA.setFont(new Font("Serif", Font.BOLD, 45));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(10,300,100,100);
        buttonB.setFont(new Font("Serif", Font.BOLD, 45));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(10,400,100,100);
        buttonC.setFont(new Font("Serif", Font.BOLD, 45));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(10,500,100,100);
        buttonD.setFont(new Font("Serif", Font.BOLD, 45));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answerLabelA.setBounds(135,200, 500, 100);
        answerLabelA.setBackground(new Color(220, 208, 255));
        answerLabelA.setForeground(new Color(16,16,16));
        answerLabelA.setFont(new Font("Serif", Font.PLAIN, 35));

        answerLabelB.setBounds(135,300, 500, 100);
        answerLabelB.setBackground(new Color(220, 208, 255));
        answerLabelB.setForeground(new Color(16,16,16));
        answerLabelB.setFont(new Font("Serif", Font.PLAIN, 35));

        answerLabelC.setBounds(135,400, 500, 100);
        answerLabelC.setBackground(new Color(220, 208, 255));
        answerLabelC.setForeground(new Color(16,16,16));
        answerLabelC.setFont(new Font("Serif", Font.PLAIN, 35));

        answerLabelD.setBounds(135,500, 500, 100);
        answerLabelD.setBackground(new Color(220, 208, 255));
        answerLabelD.setForeground(new Color(16,16,16));
        answerLabelD.setFont(new Font("Serif", Font.PLAIN, 35));

        secondsLeft.setBounds(635,610,100,100);
        secondsLeft.setBackground(new Color(169, 92, 104));
        secondsLeft.setForeground(new Color(255,255,255));
        secondsLeft.setFont(new Font("Dialog", Font.BOLD,60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        timeLabel.setBounds(635,575,100,25);
        timeLabel.setBackground(new Color(220, 208, 255));
        timeLabel.setForeground(new Color(16,16,16));
        timeLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("timer:");

        numberRight.setBounds(225, 225, 200, 100);
        numberRight.setBackground(new Color(169, 92, 104));
        numberRight.setForeground(new Color(255,255,255));
        numberRight.setFont(new Font("Dialog", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225,350,200,100);
        percentage.setBackground(new Color(169, 92, 104));
        percentage.setForeground(new Color(255,255,255));
        percentage.setFont(new Font("Dialogue", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

//        frame.add(numberRight);
//        frame.add(percentage);
        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {
        if(index == totalQuestions-1) {
            results();
        }
        else {
            textField.setText("Question " + (index+1));
            textArea.setText(questions[index]);
            answerLabelA.setText(options[index][0]);
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource() == buttonA) {
            answer = 'A';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource() == buttonB) {
            answer = 'B';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource() == buttonC) {
            answer = 'C';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource() == buttonD) {
            answer = 'D';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }

        displayAnswer();
    }

    public void displayAnswer() {
        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] == 'A') {
            answerLabelA.setForeground(new Color(0, 156, 0));
        }
        else if(answers[index] == 'B') {
            answerLabelB.setForeground(new Color(0, 156, 0));
        }
        else if(answers[index] == 'C') {
            answerLabelC.setForeground(new Color(0, 156, 0));
        }
        else {
            answerLabelD.setForeground(new Color(0, 156, 0));
        }

        Timer pause = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerLabelA.setForeground(new Color(16,16,16));
                answerLabelB.setForeground(new Color(16,16,16));
                answerLabelC.setForeground(new Color(16,16,16));
                answerLabelD.setForeground(new Color(16,16,16));

                answer = ' ';
                seconds = 10;
                secondsLeft.setText(String.valueOf(seconds));

                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                index++;
                nextQuestion();
            }
        });

        pause.setRepeats(false);
        pause.start();
    }

    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)(((double)correctGuesses / totalQuestions) * 100);

        textField.setText("RESULTS");
        textArea.setText("");
        answerLabelA.setText("");
        answerLabelB.setText("");
        answerLabelC.setText("");
        answerLabelD.setText("");

        numberRight.setText("(" + correctGuesses + "/" + totalQuestions + ")");
        percentage.setText(result + "%");

        frame.add(percentage);
        frame.add(numberRight);
    }
}
