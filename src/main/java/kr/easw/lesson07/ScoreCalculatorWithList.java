package kr.easw.lesson07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 이전 2개의 예제에서 소개된 List 컬렉션과 제너릭스를 이용해 평균 점수 계산기를 만들어보세요.
 *
 * **반드시** CalculatorImpl 클래스만 수정하여 문제를 풀어야 합니다.
 *
 * 해당 문제는 다음과 같은 제한 사항이 있습니다 :
 * - CalculatorImpl 클래스는 Calculator 인터페이스를 구현해야 합니다.
 * - CalculatorImpl 클래스는 List를 반드시 사용해야 합니다.
 * - 구동시 오류가 발생하지 않아야 합니다.
 * - 입력된 점수가 없을 경우, RuntimeException을 발생시켜야 합니다.
 */
public class ScoreCalculatorWithList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new CalculatorImpl();

        while (true) {
            System.out.println("Enter the subject and score.");
            System.out.print("> ");
            String subject = scanner.next();
            if (subject.equals("exit")) {
                break;
            }
            int score = scanner.nextInt();
            calculator.addScore(subject, score);
        }
        System.out.printf("Subject Counts: %d; Average: %.2f; ", calculator.getSubjectCount(), calculator.getAverage());
    }

    interface Calculator {
        void addScore(String subject, int score);

        double getAverage();

        int getSubjectCount();
    }

    static class CalculatorImpl implements Calculator {
        // 1. 리스트 선언 부분 ( 제네릭 사용 )
        private List<Score> scoreList = new ArrayList<>();
        @Override
        public void addScore(String subject, int score) {
            scoreList.add(new Score(subject, score));
            //throw new RuntimeException("이곳에 코드를 작성하세요.");
        }

        @Override
        public double getAverage() {
            //throw new RuntimeException("이곳에 코드를 작성하세요.");
            // 2. 리스트가 비어 있으면 예외처리
            if (scoreList.isEmpty()) {
                throw new RuntimeException("점수가 입력되지 않았습니다.");
            }
            // 3. 리스트 socreList을 돌며 원소 Score의 수를 받아 totalScore에 누적
            int totalScore = 0;
            for (Score score : scoreList) {
                totalScore += score.getScore();
            }
            //4.평균 출력
            return (double) totalScore / scoreList.size();
        }

        @Override
        public int getSubjectCount() {
            //5. ArrayList scoreList의 크기 return
            return scoreList.size();
            //throw new RuntimeException("이곳에 코드를 작성하세요.");
        }
    }

    static class Score {
        private final String subject;
        private final int score;

        public Score(String subject, int score) {
            this.subject = subject;
            this.score = score;
        }

        public String getSubject() {
            return subject;
        }

        public int getScore() {
            return score;
        }
    }
}
