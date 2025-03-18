import java.util.Scanner;
import java.io.File;

public class JournalList {
    JournalNode head;
    JournalNode tail;
    JournalNode selector;
    int index;
    
    public JournalList() {
        index = 0;
    }
    public void add(String entry) {
        JournalNode newEntry = new JournalNode(entry);
        if (head == null) {
            head = newEntry;
            tail = head;
        } else {
            newEntry.last = tail;
            tail.next = newEntry;
            tail = newEntry;
        }
        selector = tail;
    }
    public void fillList() {
        try (Scanner scan = new Scanner(new File("output.txt"))) {
            scan.useDelimiter("#ENTRYBREAK#");
            while (scan.hasNext()) {
                this.add(scan.next());
            }
            scan.close();
        } catch (Exception e) {}
    }
    public void selectPrevious() {
        selector = (selector.last != null) ? selector.last : selector;
    }
    public void selectNext() {
        selector = (selector.next != null) ? selector.next : selector;
    }
    public void selectFinal() {
        selector = (tail != null) ? tail : selector;
    }
    public void selectFirst() {
        selector = (head != null) ? head : selector;
    }
    public String getSelector() {
        return selector.toString();
    }
    public String getHead() {
        return head.toString();
    }
    public String getTail() {
        return tail.toString();
    }
    public void insert(String entry, int index) {}
    public void delete() {
        if (head == null) {
            return;
        } else if (selector == head && selector == tail) {
            head = null;
            tail = null;
            selector = null;
        } else if (selector == head) {
            head = selector.next;
            selector.next = null;
            head.last = null;
            selector = head;
        } else if (selector == tail) {
            tail = selector.last;
            selector.last = null;
            tail.next = null;
            selector = tail;
        } else {
            selector.last.next = selector.next;
            selector.next.last = selector.last;
            selector.next = null;
            selector.last = null;
            selector = tail;
        }
        JournalWriter.clear();
        JournalNode current = head;
        while (current != null) {
            JournalWriter.write(current.toString());
            current = current.next;
        }
    }
    public String toString() {
        if (head == null) {
            return "";
        }
        StringBuffer output = new StringBuffer();
        JournalNode current = head;
        while (current != null) {
            output.append(current);
            current = current.next;
        }
        return output.toString();
    }

    private class JournalNode {
        private String entry;
        private JournalNode next;
        private JournalNode last;
        public JournalNode(String entry) {
            this.entry = entry;
        }
        public String toString() {
            return entry;
        }
    }
}
