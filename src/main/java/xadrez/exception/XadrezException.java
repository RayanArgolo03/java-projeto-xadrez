package xadrez.exception;

import tabuleiro.exceptions.TabuleiroException;

public class XadrezException extends TabuleiroException {

    static final long serialVersionUID = 1L;

    public XadrezException(String message) {
        super(message);
    }

}
