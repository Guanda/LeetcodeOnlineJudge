/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.

*/

class RectangleArea
{
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H)
	{
		//the case that no overlap
		if(C < E || A > G)
			return (G - E) * (H - F) + (C - A) * (D - B);

		//the other case of no overlap
		if(D < F || H < B)
			return (G - E) * (H - F) + (C - A) * (D - B);

		int right = Math.min(C, G);
		int left = Math.max(A, E);
		int top = Math.min(H, D);
		int bottom = Math.max(F, B);

		//have overlap, minus it
		return (G - E) * (H - F) + (C - A) * (D - B) - (right - left) * (top - bottom);
	}
}
