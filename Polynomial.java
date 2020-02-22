package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node newNodeSum = null, a = null, b = null, anewNode = null, bnewNode = null, equalNodeSum = null;
		int deg1 = 0, deg2 = 0;
		float h = 0;
		boolean polys=false;
		for(a = poly1; a != null; a=a.next)
			deg1=a.term.degree;
		for(a = poly1; a != null; a=a.next) 
			for(int i = 0; i <= deg1; i++) {
				if(a.term.degree==i) {
				anewNode = new Node(a.term.coeff,i, anewNode);
				i=deg1;
				}
				else {
					if(i==anewNode.term.degree+1)
					anewNode = new Node(0,i, anewNode);
				}
			}
		for(b = poly2; b != null; b=b.next)
			deg2=b.term.degree;
		for(b = poly2; b != null; b=b.next) 
			for(int i = 0; i <= deg2; i++) {
				if(b.term.degree==i) {
				bnewNode = new Node(b.term.coeff,i, bnewNode);
				i=deg2;
				}
				else {
					if(i==bnewNode.term.degree+1)
					bnewNode = new Node(0,i, bnewNode);
				}
			}
		for(a = poly1; a != null; a=a.next) {
			for(b = poly2; b != null; b=b.next) {
				if(poly1.term.degree==poly2.term.degree&&poly1.term.coeff==poly2.term.coeff) 
					polys=true;
			}
		}
		if(deg1>deg2&&polys==false) {
			bnewNode = new Node(0,deg1,bnewNode);
			for(a = anewNode; a != null; a=a.next) {
				for(b = bnewNode; b != null; b=b.next) {
					if(a.term.degree==b.term.degree) {
						h = a.term.coeff+b.term.coeff;
						if(h!=0)
						newNodeSum = new Node(h,a.term.degree, newNodeSum);
					}
				}
			}
		}else if(deg1<deg2&&polys==false) {
			anewNode = new Node(0,deg2,anewNode);
			for(a = anewNode; a != null; a=a.next) {
				for(b = bnewNode; b != null; b=b.next) {
					if(a.term.degree==b.term.degree) {
						h = a.term.coeff+b.term.coeff;
						if(h!=0)
						newNodeSum = new Node(h,a.term.degree, newNodeSum);
					}
				}
			}
		}else if(polys==true) {
			for(a = poly1; a != null; a=a.next) {
				for(b = poly2; b != null; b=b.next) {
					if(a.term.degree==b.term.degree)
						equalNodeSum = new Node(a.term.coeff+b.term.coeff,a.term.degree, equalNodeSum);
				}
			}
			return equalNodeSum;
		}
		return newNodeSum;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		Node newNode = null, a = null, b = null, anewNode = null, bnewNode = null, fakeNode = null,prefinalNode=null,finalNode = null;
		int deg1 = 0, deg2 = 0,count=0;
		float h = 0;
		for(a = poly1; a != null; a=a.next)
			deg1=a.term.degree;
		for(a = poly1; a != null; a=a.next) 
			for(int i = 0; i <= deg1; i++) {
				if(a.term.degree==i) {
				anewNode = new Node(a.term.coeff,i, anewNode);
				i=deg1;
				}
				else {
					if(i==anewNode.term.degree+1)
					anewNode = new Node(0,i, anewNode);
				}
			}
		for(b = poly2; b != null; b=b.next)
			deg2=b.term.degree;
		for(b = poly2; b != null; b=b.next) 
			for(int i = 0; i <= deg2; i++) {
				if(b.term.degree==i) {
				bnewNode = new Node(b.term.coeff,i, bnewNode);
				i=deg2;
				}
				else {
					if(i==bnewNode.term.degree+1)
					bnewNode = new Node(0,i, bnewNode);
				}
			}
		int c = 0;
		for(a = bnewNode; a != null; a=a.next) {
			for(b = anewNode; b != null; b=b.next) {
				h = b.term.coeff*a.term.coeff;
				fakeNode = new Node(h, c, fakeNode);
				deg1=c;
				c++;
			}
			count++;
			c=count;
		}
		h=0;
		count=deg1;
		deg2=0;
		for(int i = 0; i<=deg1;i++) {
			for(a = fakeNode; a != null; a=a.next) {
				if(i==a.term.degree) 
					h+=a.term.coeff;
				if(h!=0) {
				newNode = new Node(h,count, newNode);
				}
			}
			h=0;
			count--;
		}
		h=0;
		count=deg1;
		deg2=0;
		for(int i = 0; i<=deg1;i++) {
			for(a=newNode; a!=null; a=a.next) {
				if(i==a.term.degree) 
					h+=a.term.coeff;
				if(h!=0&&deg2!=count) {
					deg2=count;
				prefinalNode = new Node(a.term.coeff,a.term.degree,prefinalNode);
				}
			}
			h=0;
			count--;
		}
		for(a=prefinalNode; a != null; a=a.next) 
			finalNode = new Node(a.term.coeff,a.term.degree,finalNode);
		return finalNode;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		Node a = null;
		float h = 0;
		for(a = poly; a != null; a = a.next) 
			h += (float) (Math.pow(x,a.term.degree)*a.term.coeff);
		return h;
	}


	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
