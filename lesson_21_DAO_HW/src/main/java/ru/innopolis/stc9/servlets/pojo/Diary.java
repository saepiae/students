package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

/**
 * Created by ich on 04.05.2018.
 */
public class Diary {
    final static Logger logger = Logger.getLogger(Diary.class);
    private int item;
    private int lessonItem;
    private int studentItem;
    private boolean isOnLesson;
    private int mark;

    public Diary(int item, int lessonItem, int studentItem, boolean isOnLesson, int mark) {
        this.item = item;
        this.lessonItem = lessonItem;
        this.studentItem = studentItem;
        this.isOnLesson = isOnLesson;
        this.mark = mark;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getLessonItem() {
        return lessonItem;
    }

    public void setLessonItem(int lessonItem) {
        this.lessonItem = lessonItem;
    }

    public int getStudentItem() {
        return studentItem;
    }

    public void setStudentItem(int studentItem) {
        this.studentItem = studentItem;
    }

    public boolean isOnLesson() {
        return isOnLesson;
    }

    public void setOnLesson(boolean onLesson) {
        isOnLesson = onLesson;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diary diary = (Diary) o;
        return item == diary.item &&
                lessonItem == diary.lessonItem &&
                studentItem == diary.studentItem &&
                isOnLesson == diary.isOnLesson &&
                mark == diary.mark;
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, lessonItem, studentItem, isOnLesson, mark);
    }

    @Override
    public String toString() {
        return "DiaryDAO{" +
                "item=" + item +
                ", lessonItem=" + lessonItem +
                ", studentItem=" + studentItem +
                ", isOnLesson=" + isOnLesson +
                ", mark=" + mark +
                '}';
    }
}
