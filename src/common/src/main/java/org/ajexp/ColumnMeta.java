package org.ajexp;

import org.ajexp.annotations.AjxColumn;

import java.lang.reflect.Member;
import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public class ColumnMeta {
    private AjxColumn annotation;
    private Member member;
    private List<ColumnMeta> subColumns;
    private boolean isFormatted;

    public ColumnMeta(AjxColumn annotation, Member member) {
        this.annotation = annotation;
        this.member = member;
        String format = annotation.format();
        this.isFormatted = (format != null && !format.isEmpty());
    }

    public ColumnMeta(AjxColumn annotation, Member member, List<ColumnMeta> subColumns) {
        this(annotation, member);
        this.subColumns = subColumns;
    }

    public AjxColumn getAnnotation() {
        return annotation;
    }

    public Member getMember() {
        return member;
    }

    public List<ColumnMeta> getSubColumns() {
        return subColumns;
    }

    public boolean isFormatted() {
        return isFormatted;
    }
}
