### Expected output

```
  ========================================================================
                    INFIX -> POSTFIX -> REVERSED INFIX
  ========================================================================
  Test Case 1:
    Original Infix  : A + B * C - D
    Postfix Output  : A B C * + D -
    Reversed Infix  : ((A + (B * C)) - D)
  ------------------------------------------------------------------------
  Test Case 2:
    Original Infix  : (A + B) * (C - D)
    Postfix Output  : A B + C D - *
    Reversed Infix  : ((A + B) * (C - D))
  ------------------------------------------------------------------------
  Test Case 3:
    Original Infix  : A * B + C / D
    Postfix Output  : A B * C D / +
    Reversed Infix  : ((A * B) + (C / D))
  ------------------------------------------------------------------------
  Test Case 4:
    Original Infix  : a + b * c + ( d * e + f ) * g
    Postfix Output  : a b c * + d e * f + g * +
    Reversed Infix  : ((a + (b * c)) + (((d * e) + f) * g))
  ------------------------------------------------------------------------
  Test Case 5:
    Original Infix  : ( 5 + 3 ) * 2 ^ 2 / ( 9 - 1 )
    Postfix Output  : 5 3 + 2 2 ^ * 9 1 - /
    Reversed Infix  : (((5 + 3) * (2 ^ 2)) / (9 - 1))
  ------------------------------------------------------------------------
  Test Case 6:
    Original Infix  : 4.99 * 1.06 + 5.99 + 6.99 * 1.06
    Postfix Output  : 4.99 1.06 * 5.99 + 6.99 1.06 * +
    Reversed Infix  : (((4.99 * 1.06) + 5.99) + (6.99 * 1.06))
  ------------------------------------------------------------------------
```
