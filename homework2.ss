;Austin Emery
;Programing Languages
;Homework 2
;9/28/17

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;PROBLEM 1

;NOT A FUNCTION FOR A QUESTION, but used in the programs, checks if x is a member of list
(define (member? x L)
     (if (null? L) #f                              
         (if (equal? x (car L)) #t                   
              (member? x (cdr L)))))

; 1a. is-set
(define (is-set? L)
	(if (null? L) #t
		(if (member? (car L) (cdr L)) #f
			(is-set? (cdr L)))))

; 1b. make-set
(define (make-set L)
	(if (is-set? L) L
		(if (member? (car L) (cdr L)) (make-set (cdr L))
			(make-set (append (cdr L) (list (car L)))))))

; 1c. subset?
(define (subset? A B)
	(if (null? A) #t
		(if (member? (car A) B) (subset? (cdr A) B)
			#f)))

; 1d. union (not sure if this is okay so I did it without using make-set down below)
;(define (union A B)
;	(make-set (append A B)))

; 1d. union
(define (union A B)
	(if (null? A) B
		(if (null? B) A
			(if (member? (car A) B) (union (cdr A) B) 
				(union (cdr A) (append B (list (car A))))))))

; 1e. intersection
(define (intersection A B)
	(if (null? A) '()
		(if (null? B) '()
		(if (member? (car A) B) (append (list (car A)) (intersection (cdr A) B))
			(intersection (cdr A) B)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;PROBLEM 2

(define T
	'(13
		(5
			(1 () ())
			(8 ()
				(9 () ())))
		(22
			(17 () ())
			(25 () ()))))

;NOT A FUNCTION FOR A QUESTION, but used in the programs
(define (val T)
	(if (null? T) '()
		(car T)))

;NOT A FUNCTION FOR A QUESTION, but used in the programs
(define (left T)
	(if (null? (car(cdr T))) '()
		(car(cdr T))))

;NOT A FUNCTION FOR A QUESTION, but used in the programs
(define (right T)
	(if (null? (car(cdr(cdr T)))) '()
		(car(cdr(cdr T)))))
   
; 2a. tree-member
(define (tree-member? x T)
	(if (null? T) #f
		(if (equal? x (val T)) #t
			(if (< x (val T)) (tree-member? x (left T))
				(tree-member? x (right T))))))

; 2b. preorder
(define (preorder T) ;Root, Left, Right
	(if (null? T) '()
		(append (list (val T)) (preorder (left T)) (preorder(right T)))))

; 2c. inorder
(define (inorder T) ;Root, Left, Right
	(if (null? T) '()
		(append (inorder (left T)) (list (val T)) (inorder(right T)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;PROBLEM 3

; 3. deep-delete
(define (deep-delete V L)
	(cond ((null? L) L)
		  ((list? (car L))
		  (cons (deep-delete V (car L)) (deep-delete V (cdr L))))
		  ((equal? V (car L)) (deep-delete V (cdr L)))
		  (else (cons (car L) (deep-delete V (cdr L))))))