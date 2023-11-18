import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App1 {

    public static void main(String[] args) throws IOException {
        List<Email> emails = getEmails();

        //총 줄 수 구하기
        emailTotalSize(emails);
        //최소 사람 ID 찾기
        findMinId(emails);
        //최대 사람 ID 찾기
        findMaxId(emails);
        //‘265213’ ID 확인하기
        findById(emails, 265213);
        //이메일 보낸 사람의 수 -> 중복이 없어야 함
        senderTotalSize(emails);
        //ID 10000 다음 찾기
        findNext(emails, 10000);
        //ID 10000 이전 찾기
        findPrevious(emails, 10000);
        //ID 다음 값 혹은 이전 값 쉽게 찾기 -> TreeSet 이용
        findNextPre(emails, 10000);

    }

    private static void findNextPre(List<Email> emails, int id) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (Email email : emails) {
            treeSet.add(email.getFrom());
            treeSet.add(email.getTo());
        }
        System.out.println(id + " 이전 값(TreeSet 이용): " + treeSet.lower(id));
        System.out.println(id + " 이후 값(TreeSet 이용): " + treeSet.higher(id));
    }

    private static void findPrevious(List<Email> emails, int id) {
        //10000 이전 값 = 10000보다 작은 수 중 최댓값
        int max = Integer.MIN_VALUE;
        for (Email email : emails) {
            if (email.getFrom() > max && email.getFrom() < id) {
                max = email.getFrom();
            }
            if (email.getTo() > max && email.getTo() < id) {
                max = email.getTo();
            }
        }
        System.out.println(id + " 이전 값: " + max);
    }

    private static void findNext(List<Email> emails, int id) {
        //10000 다음 값 = 10000보다 큰 수 중 최솟값
        int min = Integer.MAX_VALUE;
        for (Email email : emails) {
            if (email.getFrom() < min && email.getFrom() > id) {
                min = email.getFrom();
            }
            if (email.getTo() < min && email.getTo() > id) {
                min = email.getTo();
            }
        }
        System.out.println(id + " 다음 값: " + min);
    }

    private static void senderTotalSize(List<Email> emails) {
        Set<Integer> emailSet = new HashSet<>();
        for (Email email : emails) {
            emailSet.add(email.getFrom());
        }
        System.out.println("이메일 보낸 사람 수: " + emailSet.size());
    }

    private static void findById(List<Email> emails, int id) {
        for (Email email : emails) {
            if (email.getFrom() == id || email.getTo() == id) {
                System.out.println(id + "번 발견!");
                return;
            }
        }
        System.out.println(id + "번 없음!");
    }

    private static void findMaxId(List<Email> emails) {
        int max = Integer.MIN_VALUE;
        for (Email email : emails) {
            if (email.getFrom() > max) {
                max = email.getFrom();
            }
            if (email.getTo() > max) {
                max = email.getTo();
            }
        }
        System.out.println("최대 사람 ID: " + max);
    }

    private static void findMinId(List<Email> emails) {
        int min = Integer.MAX_VALUE;
        for (Email email : emails) {
            if (email.getFrom() < min) {
                min = email.getFrom();
            }
            if (email.getTo() < min) {
                min = email.getTo();
            }
        }
        System.out.println("최소 사람 ID: " + min);
    }

    private static void emailTotalSize(List<Email> emails) {
        System.out.println("총 이메일 수: " + emails.size());
    }

    private static List<Email> getEmails() throws IOException {
        BufferedReader br
                = new BufferedReader(new FileReader("/Users/sangcheol/Desktop/Email-EuAll.txt"));

        List<Email> emails = new ArrayList<>();

        String line = null;
        while (true) {
            line = br.readLine();
            if (line == null)
                break;
            if (line.startsWith("#"))
                continue;

            String[] split = line.split("\t");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            emails.add(new Email(from, to));
        }

        br.close();
        return emails;
    }
}
