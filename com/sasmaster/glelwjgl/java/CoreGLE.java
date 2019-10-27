/*      */ package com.sasmaster.glelwjgl.java;
/*      */ 
/*      */ import java.nio.DoubleBuffer;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import org.lwjgl.util.glu.GLUtessellator;
/*      */ import org.lwjgl.util.glu.GLUtessellatorCallback;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CoreGLE
/*      */   implements GLE
/*      */ {
/*   57 */   public static final String VERSION = new String("$Id: CoreGLE.java,v 1.5 1998/05/20 00:19:43 descarte Exp descarte $");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   62 */   private static final String GLE_VERSION = new String("095");
/*      */ 
/*      */ 
/*      */   
/*   66 */   private final GLEContext context_ = new GLEContext();
/*      */ 
/*      */ 
/*      */   
/*   70 */   private int _POLYCYL_TESS = 20;
/*      */   
/*   72 */   public void set_POLYCYL_TESS(int _POLYCYL_TESS) { this._POLYCYL_TESS = _POLYCYL_TESS; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   private int __ROUND_TESS_PIECES = 5;
/*      */   
/*   82 */   public void set__ROUND_TESS_PIECES(int __ROUND_TESS_PIECES) { this.__ROUND_TESS_PIECES = __ROUND_TESS_PIECES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   91 */   private static GLU glu_ = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   private tessellCallBack tessCallback = new tessellCallBack(glu_);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   public int gleGetJoinStyle() { return this.context_.getJoinStyle(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   public void gleSetJoinStyle(int style) { this.context_.setJoinStyle(style); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleTextureMode(int mode) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  130 */   private float SLICE = 1.0F;
/*  131 */   private float SLICE_PROGRESS = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void gen_polycone(int npoints, double[][] pointArray, float[][] colourArray, double radius, double[][][] xformArray, float texSlice, float start) {
/*  141 */     this.SLICE = texSlice;
/*  142 */     this.SLICE_PROGRESS = start;
/*      */     
/*  144 */     double[][] circle = new double[this._POLYCYL_TESS][2];
/*  145 */     double[][] norm = new double[this._POLYCYL_TESS][2];
/*      */     
/*  147 */     double[] v21 = new double[3];
/*  148 */     double len = 0.0D;
/*  149 */     double[] up = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  156 */     if (xformArray != null) {
/*  157 */       radius = 1.0D;
/*      */     }
/*  159 */     double s = Math.sin(6.283185307179586D / this._POLYCYL_TESS);
/*  160 */     double c = Math.cos(6.283185307179586D / this._POLYCYL_TESS);
/*      */     
/*  162 */     norm[0][0] = 1.0D;
/*  163 */     norm[0][1] = 0.0D;
/*  164 */     circle[0][0] = radius;
/*  165 */     circle[0][1] = 0.0D;
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/*  170 */     for (i = 1; i < this._POLYCYL_TESS; i++) {
/*  171 */       norm[i][0] = norm[i - 1][0] * c - norm[i - 1][1] * s;
/*  172 */       norm[i][1] = norm[i - 1][0] * s + norm[i - 1][1] * c;
/*  173 */       circle[i][0] = radius * norm[i][0];
/*  174 */       circle[i][1] = radius * norm[i][1];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  183 */     i = 0;
/*  184 */     i = intersect.FIND_NON_DEGENERATE_POINT(i, npoints, len, v21, pointArray);
/*      */     
/*  186 */     len = matrix.VEC_LENGTH(v21);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     if (i == npoints) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  199 */     up[2] = 1.0D; up[1] = 1.0D; up[0] = 1.0D;
/*      */     
/*  201 */     up[2] = 0.0D; up[0] = 0.0D;
/*  202 */     up[1] = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  208 */     int savedStyle = gleGetJoinStyle();
/*  209 */     gleSetJoinStyle(savedStyle | 0x1000);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  215 */     if (!GL11.glIsEnabled(2896)) {
/*  216 */       gleSuperExtrusion(this._POLYCYL_TESS, circle, (double[][])null, up, npoints, pointArray, colourArray, xformArray);
/*      */     }
/*      */     else {
/*      */       
/*  220 */       gleSuperExtrusion(this._POLYCYL_TESS, circle, norm, up, npoints, pointArray, colourArray, xformArray);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  228 */     gleSetJoinStyle(savedStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  248 */   public void glePolyCylinder(int npoints, double[][] pointArray, float[][] colourArray, double radius, float texSlice, float start) throws GLEException { gen_polycone(npoints, pointArray, colourArray, radius, (double[][][])null, texSlice, start); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void glePolyCone(int npoints, double[][] pointArray, float[][] colourArray, double[] radiusArray, float texSlice, float start) throws GLEException {
/*  266 */     double[][][] xforms = new double[npoints][2][3];
/*  267 */     for (int i = 0; i < npoints; i++) {
/*  268 */       xforms[i][0][0] = radiusArray[i];
/*  269 */       xforms[i][0][1] = 0.0D;
/*  270 */       xforms[i][0][2] = 0.0D;
/*  271 */       xforms[i][1][0] = 0.0D;
/*  272 */       xforms[i][1][1] = radiusArray[i];
/*  273 */       xforms[i][1][2] = 0.0D;
/*      */     } 
/*  275 */     gen_polycone(npoints, pointArray, colourArray, 1.0D, xforms, texSlice, start);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  301 */   public void gleExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray) throws GLEException { gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, (double[][][])null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleTwistExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[] twistArray) throws GLEException {
/*  332 */     double[][][] xforms = new double[npoints][2][3];
/*  333 */     double angle = 0.0D;
/*  334 */     double si = 0.0D;
/*  335 */     double co = 0.0D;
/*      */     
/*  337 */     for (int j = 0; j < npoints; j++) {
/*  338 */       angle = 0.017453292519943295D * twistArray[j];
/*  339 */       si = Math.sin(angle);
/*  340 */       co = Math.cos(angle);
/*  341 */       xforms[j][0][0] = co;
/*  342 */       xforms[j][0][1] = -si;
/*  343 */       xforms[j][0][2] = 0.0D;
/*  344 */       xforms[j][1][0] = si;
/*  345 */       xforms[j][1][1] = co;
/*  346 */       xforms[j][1][2] = 0.0D;
/*      */     } 
/*  348 */     gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xforms);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleSuperExtrusion(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[][][] xformArray) throws GLEException {
/*  382 */     this.context_.ncp = ncp;
/*  383 */     this.context_.contour = contour;
/*  384 */     this.context_.contourNormal = contourNormal;
/*  385 */     this.context_.up = up;
/*  386 */     this.context_.npoints = npoints;
/*  387 */     this.context_.pointArray = pointArray;
/*  388 */     this.context_.colourArray = colourArray;
/*  389 */     this.context_.xformArray = xformArray;
/*      */     
/*  391 */     switch (gleGetJoinStyle() & 0xF) {
/*      */       case 1:
/*  393 */         extrusion_raw_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  399 */         extrusion_angle_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 4:
/*  409 */         extrusion_round_or_cut_join(ncp, contour, contourNormal, up, npoints, pointArray, colourArray, xformArray);
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  415 */     throw new GLEException("Join style is complete rubbish!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleSpiral(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta) throws GLEException {
/*  452 */     int npoints = (int)(this._POLYCYL_TESS / 360.0D * Math.abs(sweepTheta) + 4.0D);
/*  453 */     double[][] points = (double[][])null;
/*  454 */     double[][][] xforms = (double[][][])null;
/*  455 */     double delta = 0.0D;
/*  456 */     double deltaAngle = 0.0D;
/*  457 */     double cdelta = 0.0D;
/*  458 */     double sdelta = 0.0D;
/*  459 */     double sprev = 0.0D;
/*  460 */     double cprev = 0.0D;
/*  461 */     double scurr = 0.0D;
/*  462 */     double ccurr = 0.0D;
/*  463 */     double[][] mA = new double[2][2];
/*  464 */     double[][] mB = new double[2][2];
/*  465 */     double[][] run = new double[2][2];
/*  466 */     double[] deltaTrans = new double[2];
/*  467 */     double[] trans = new double[2];
/*      */     
/*  469 */     points = new double[npoints][3];
/*  470 */     if (startTransform == null) {
/*  471 */       xforms = (double[][][])null;
/*      */     } else {
/*  473 */       xforms = new double[npoints][2][3];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  479 */     deltaAngle = 0.017453292519943295D * sweepTheta / (npoints - 3);
/*  480 */     startTheta *= 0.017453292519943295D;
/*  481 */     startTheta -= deltaAngle;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  486 */     cprev = Math.cos(startTheta);
/*  487 */     sprev = Math.sin(startTheta);
/*      */     
/*  489 */     cdelta = Math.cos(deltaAngle);
/*  490 */     sdelta = Math.sin(deltaAngle);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  495 */     delta = deltaAngle / 6.283185307179586D;
/*  496 */     dzdTheta *= delta;
/*  497 */     drdTheta *= delta;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  502 */     startZ -= dzdTheta;
/*  503 */     startRadius -= drdTheta;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  508 */     for (int i = 0; i < npoints; i++) {
/*  509 */       points[i][0] = startRadius * cprev;
/*  510 */       points[i][1] = startRadius * sprev;
/*  511 */       points[i][2] = startZ;
/*      */       
/*  513 */       startZ += dzdTheta;
/*  514 */       startRadius += drdTheta;
/*  515 */       ccurr = cprev * cdelta - sprev * sdelta;
/*  516 */       scurr = cprev * sdelta + sprev * cdelta;
/*  517 */       cprev = ccurr;
/*  518 */       sprev = scurr;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  525 */     if (startTransform != null) {
/*  526 */       if (dTransformdTheta == null) {
/*  527 */         for (int i = 0; i < npoints; i++) {
/*  528 */           xforms[i][0][0] = startTransform[0][0];
/*  529 */           xforms[i][0][1] = startTransform[0][1];
/*  530 */           xforms[i][0][2] = startTransform[0][2];
/*  531 */           xforms[i][1][0] = startTransform[1][0];
/*  532 */           xforms[i][1][1] = startTransform[1][1];
/*  533 */           xforms[i][1][2] = startTransform[1][2];
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  550 */         deltaTrans[0] = delta * dTransformdTheta[0][2];
/*  551 */         deltaTrans[1] = delta * dTransformdTheta[1][2];
/*  552 */         trans[0] = startTransform[0][2];
/*  553 */         trans[1] = startTransform[1][2];
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  558 */         delta /= 32.0D;
/*  559 */         mA[0][0] = 1.0D + delta * dTransformdTheta[0][0];
/*  560 */         mA[0][1] = delta * dTransformdTheta[0][1];
/*  561 */         mA[1][0] = delta * dTransformdTheta[1][0];
/*  562 */         mA[1][1] = 1.0D + delta * dTransformdTheta[1][1];
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  567 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*  568 */         mA = matrix.MATRIX_PRODUCT_2X2(mB, mB);
/*  569 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*  570 */         mA = matrix.MATRIX_PRODUCT_2X2(mB, mB);
/*  571 */         mB = matrix.MATRIX_PRODUCT_2X2(mA, mA);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  576 */         run = matrix.COPY_MATRIX_2X2(startTransform);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  581 */         xforms[0][0][0] = startTransform[0][0];
/*  582 */         xforms[0][0][1] = startTransform[0][1];
/*  583 */         xforms[0][0][2] = startTransform[0][2];
/*  584 */         xforms[0][1][0] = startTransform[1][0];
/*  585 */         xforms[0][1][1] = startTransform[1][1];
/*  586 */         xforms[0][1][2] = startTransform[1][2];
/*      */         
/*  588 */         for (int j = 0; j < npoints; j++) {
/*  589 */           xforms[j][0][0] = run[0][0];
/*  590 */           xforms[j][0][1] = run[0][1];
/*  591 */           xforms[j][1][0] = run[1][0];
/*  592 */           xforms[j][1][1] = run[1][1];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  599 */           mA = matrix.MATRIX_PRODUCT_2X2(mB, run);
/*  600 */           run = matrix.COPY_MATRIX_2X2(mA);
/*      */           
/*  602 */           xforms[j][0][2] = trans[0];
/*  603 */           xforms[j][1][2] = trans[1];
/*      */           
/*  605 */           trans[0] = trans[0] + deltaTrans[0];
/*  606 */           trans[1] = trans[1] + deltaTrans[1];
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  614 */     int saveStyle = gleGetJoinStyle();
/*  615 */     int style = saveStyle;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  625 */     style &= 0xFFFFFFF0;
/*  626 */     style |= 0x2;
/*  627 */     gleSetJoinStyle(style);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  632 */     gleSuperExtrusion(ncp, contour, contourNormal, up, npoints, points, (float[][])null, xforms);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  638 */     gleSetJoinStyle(saveStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleLathe(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta) throws GLEException {
/*  675 */     double[] localup = new double[3];
/*  676 */     double len = 0.0D;
/*  677 */     double[] trans = new double[2];
/*  678 */     double[][] start = new double[2][3];
/*  679 */     double[][] delt = new double[2][3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  687 */     if (up != null) {
/*  688 */       if (up[1] != 0.0D) {
/*  689 */         localup[0] = up[0];
/*  690 */         localup[1] = 0.0D;
/*  691 */         localup[2] = up[2];
/*      */         
/*  693 */         len = matrix.VEC_LENGTH(localup);
/*  694 */         if (len != 0.0D) {
/*  695 */           len = 1.0D / len;
/*  696 */           localup[0] = localup[0] * len;
/*  697 */           localup[2] = localup[2] * len;
/*  698 */           localup = matrix.VEC_SCALE(len, localup);
/*      */         } else {
/*  700 */           localup[0] = 0.0D;
/*  701 */           localup[2] = 1.0D;
/*      */         } 
/*      */       } else {
/*  704 */         localup = matrix.VEC_COPY(up);
/*      */       } 
/*      */     } else {
/*  707 */       localup[0] = 0.0D;
/*  708 */       localup[2] = 1.0D;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  718 */     trans[0] = localup[2] * drdTheta - localup[0] * dzdTheta;
/*  719 */     trans[1] = localup[0] * drdTheta + localup[2] * dzdTheta;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  724 */     if (startTransform != null) {
/*  725 */       if (dTransformdTheta != null) {
/*  726 */         delt = matrix.COPY_MATRIX_2X3(dTransformdTheta);
/*  727 */         delt[0][2] = delt[0][2] + trans[0];
/*  728 */         delt[1][2] = delt[1][2] + trans[1];
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  733 */         delt[0][0] = 0.0D;
/*  734 */         delt[0][1] = 0.0D;
/*  735 */         delt[0][2] = trans[0];
/*  736 */         delt[1][0] = 0.0D;
/*  737 */         delt[1][1] = 0.0D;
/*  738 */         delt[1][2] = trans[1];
/*      */       } 
/*  740 */       gleSpiral(ncp, contour, contourNormal, up, startRadius, 0.0D, startZ, 0.0D, startTransform, delt, startTheta, sweepTheta);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  748 */       start[0][0] = 1.0D;
/*  749 */       start[0][1] = 0.0D;
/*  750 */       start[0][2] = 0.0D;
/*  751 */       start[1][0] = 0.0D;
/*  752 */       start[1][1] = 1.0D;
/*  753 */       start[1][2] = 0.0D;
/*      */       
/*  755 */       delt[0][0] = 0.0D;
/*  756 */       delt[0][1] = 0.0D;
/*  757 */       delt[0][2] = trans[0];
/*  758 */       delt[1][0] = 0.0D;
/*  759 */       delt[1][1] = 0.0D;
/*  760 */       delt[1][2] = trans[1];
/*  761 */       gleSpiral(ncp, contour, contourNormal, up, startRadius, 0.0D, startZ, 0.0D, start, delt, startTheta, sweepTheta);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  792 */   public void gleHelicoid(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta) throws GLEException { super_helix(rToroid, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta, "Spiral"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  821 */   public void gleToroid(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta) throws GLEException { super_helix(rToroid, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta, "Lathe"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gleScrew(int ncp, double[][] contour, double[][] contourNormal, double[] up, double startz, double endz, double twist) throws GLEException {
/*  850 */     int numsegs = (int)Math.abs(twist / 18.0D) + 4;
/*  851 */     double[][] path = new double[numsegs][3];
/*  852 */     double[] twarr = new double[numsegs];
/*  853 */     double delta = 0.0D;
/*  854 */     double currz = 0.0D;
/*  855 */     double currang = 0.0D;
/*  856 */     double delang = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  861 */     delta = (endz - startz) / (numsegs - 3);
/*  862 */     currz = startz - delta;
/*  863 */     delang = twist / (numsegs - 3);
/*  864 */     currang = -delang;
/*      */     
/*  866 */     for (int i = 0; i < numsegs; i++) {
/*  867 */       path[i][0] = 0.0D;
/*  868 */       path[i][1] = 0.0D;
/*  869 */       path[i][2] = currz;
/*  870 */       currz += delta;
/*  871 */       twarr[i] = currang;
/*  872 */       currang += delang;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  878 */     gleTwistExtrusion(ncp, contour, contourNormal, up, numsegs, path, (float[][])null, twarr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void super_helix(double rToroid, double startRadius, double drdTheta, double startZ, double dzdTheta, double[][] startTransform, double[][] dTransformdTheta, double startTheta, double sweepTheta, String callback) {
/*  896 */     double[][] circle = new double[this._POLYCYL_TESS][2];
/*  897 */     double[][] norm = new double[this._POLYCYL_TESS][2];
/*  898 */     double c = 0.0D;
/*  899 */     double s = 0.0D;
/*  900 */     double[] up = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     s = Math.sin(6.283185307179586D / this._POLYCYL_TESS);
/*  906 */     c = Math.cos(6.283185307179586D / this._POLYCYL_TESS);
/*      */     
/*  908 */     norm[0][0] = 1.0D;
/*  909 */     norm[0][1] = 0.0D;
/*  910 */     circle[0][0] = rToroid;
/*  911 */     circle[0][1] = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  916 */     for (int i = 1; i < this._POLYCYL_TESS; i++) {
/*  917 */       norm[i][0] = norm[i - 1][0] * c - norm[i - 1][1] * s;
/*  918 */       norm[i][1] = norm[i - 1][0] * s + norm[i - 1][1] * c;
/*  919 */       circle[i][0] = rToroid * norm[i][0];
/*  920 */       circle[i][1] = rToroid * norm[i][1];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  926 */     up[2] = 0.0D; up[1] = 0.0D;
/*  927 */     up[0] = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  932 */     int saveStyle = gleGetJoinStyle();
/*  933 */     int style = saveStyle;
/*  934 */     style |= 0x1000;
/*  935 */     style |= 0x400;
/*  936 */     gleSetJoinStyle(style);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  941 */     if (!GL11.glIsEnabled(2896)) {
/*  942 */       if (callback.equals("Spiral")) {
/*  943 */         gleSpiral(this._POLYCYL_TESS, circle, (double[][])null, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */ 
/*      */       
/*      */       }
/*  947 */       else if (callback.equals("Lathe")) {
/*  948 */         gleLathe(this._POLYCYL_TESS, circle, (double[][])null, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */       }
/*      */       else {
/*      */         
/*  952 */         throw new GLEException("Specified callback " + callback + " is not registered. Use either ``Spiral'' or ``Lathe''");
/*      */       }
/*      */     
/*      */     }
/*  956 */     else if (callback.equals("Spiral")) {
/*  957 */       gleSpiral(this._POLYCYL_TESS, circle, norm, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */ 
/*      */     
/*      */     }
/*  961 */     else if (callback.equals("Lathe")) {
/*  962 */       gleLathe(this._POLYCYL_TESS, circle, norm, up, startRadius, drdTheta, startZ, dzdTheta, startTransform, dTransformdTheta, startTheta, sweepTheta);
/*      */     }
/*      */     else {
/*      */       
/*  966 */       throw new GLEException("Specified callback " + callback + " is not registered. Use either ``Spiral'' or ``Lathe''");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  974 */     gleSetJoinStyle(saveStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double[] up_sanity_check(double[] up, int npoints, double[][] pointArray) {
/* 1001 */     double len = 0.0D;
/* 1002 */     double[] diff = null;
/* 1003 */     double[] vtmp = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1009 */     diff = matrix.VEC_DIFF(pointArray[1], pointArray[0]);
/* 1010 */     len = matrix.VEC_LENGTH(diff);
/* 1011 */     if (len == 0.0D)
/*      */     {
/*      */ 
/*      */       
/* 1015 */       for (int i = 1; i < npoints - 2; i++) {
/* 1016 */         diff = matrix.VEC_DIFF(pointArray[i + 1], pointArray[i]);
/* 1017 */         len = matrix.VEC_LENGTH(diff);
/* 1018 */         if (len != 0.0D) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1027 */     len = 1.0D / len;
/* 1028 */     diff = matrix.VEC_SCALE(len, diff);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1034 */     vtmp = matrix.VEC_PERP(up, diff);
/*      */     
/* 1036 */     len = matrix.VEC_LENGTH(vtmp);
/* 1037 */     if (len == 0.0D) {
/*      */ 
/*      */ 
/*      */       
/* 1041 */       System.err.println("Extrusion: Warning: ");
/* 1042 */       System.err.println("contour up vector parallel to tubing direction");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1047 */       vtmp = matrix.VEC_COPY(diff);
/*      */     } 
/* 1049 */     return vtmp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void extrusion_raw_join(int ncp, double[][] contour, double[][] contourNormal, double[] up, int npoints, double[][] pointArray, float[][] colourArray, double[][][] xformArray) throws GLEException {
/* 1063 */     int i = 0;
/* 1064 */     int j = 0;
/* 1065 */     int inext = 0;
/* 1066 */     double[][] m = (double[][])null;
/* 1067 */     double len = 0.0D;
/* 1068 */     double[] diff = new double[3];
/* 1069 */     double[] bi_0 = new double[3];
/* 1070 */     double[] yup = new double[3];
/* 1071 */     double[] nrmv = new double[3];
/* 1072 */     boolean no_norm = (contourNormal == null);
/* 1073 */     boolean no_cols = (colourArray == null);
/* 1074 */     boolean no_xform = (xformArray == null);
/* 1075 */     double[][] mem_anchor = (double[][])null;
/* 1076 */     double[][] front_loop = (double[][])null;
/* 1077 */     double[][] back_loop = (double[][])null;
/* 1078 */     double[][] front_norm = (double[][])null;
/* 1079 */     double[][] back_norm = (double[][])null;
/* 1080 */     double[][] tmp = (double[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1087 */     nrmv[1] = 0.0D; nrmv[0] = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1094 */     if (!no_xform) {
/* 1095 */       front_loop = new double[ncp][3];
/* 1096 */       back_loop = new double[ncp][3];
/* 1097 */       front_norm = new double[ncp][3];
/* 1098 */       back_norm = new double[ncp][3];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1105 */     if (up == null) {
/* 1106 */       yup[0] = 0.0D;
/* 1107 */       yup[1] = 1.0D;
/* 1108 */       yup[2] = 0.0D;
/*      */     } else {
/* 1110 */       yup = matrix.VEC_COPY(up);
/*      */     } 
/* 1112 */     up = matrix.VEC_COPY(yup);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1118 */     yup = up_sanity_check(up, npoints, pointArray);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1124 */     i = 1;
/* 1125 */     inext = i;
/*      */     
/* 1127 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, pointArray);
/*      */     
/* 1129 */     len = matrix.VEC_LENGTH(diff);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1134 */     if (!no_xform) {
/* 1135 */       for (j = 0; j < ncp; j++) {
/* 1136 */         front_loop[j] = 
/* 1137 */           matrix.MAT_DOT_VEC_2X3(xformArray[inext - 1], contour[j]);
/*      */         
/* 1139 */         front_loop[j][2] = 0.0D;
/*      */       } 
/* 1141 */       if (!no_norm) {
/* 1142 */         for (j = 0; j < ncp; j++) {
/* 1143 */           front_norm[j] = 
/* 1144 */             matrix.NORM_XFORM_2X2(xformArray[inext - 1], contourNormal[j]);
/*      */           
/* 1146 */           front_norm[j][2] = 0.0D;
/* 1147 */           back_norm[j][2] = 0.0D;
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1155 */     while (inext < npoints - 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1162 */       bi_0 = intersect.bisecting_plane(pointArray[i - 1], pointArray[i], pointArray[inext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1170 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1180 */       m = matrix.uviewpoint_d(pointArray[i], pointArray[inext], yup);
/* 1181 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/* 1182 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1190 */       mbuffer.flip();
/* 1191 */       GL11.glPushMatrix();
/* 1192 */       GL11.glMultMatrix(mbuffer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1200 */       if (no_xform) {
/* 1201 */         if (no_cols) {
/* 1202 */           if (no_norm) {
/* 1203 */             draw_raw_segment_plain(ncp, contour, inext, len);
/*      */           }
/* 1205 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1206 */             draw_raw_segment_facet_n(ncp, contour, contourNormal, inext, len);
/*      */           } else {
/*      */             
/* 1209 */             draw_raw_segment_edge_n(ncp, contour, contourNormal, inext, len);
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1214 */         else if (no_norm) {
/* 1215 */           draw_raw_segment_color(ncp, contour, colourArray, inext, len);
/*      */         
/*      */         }
/* 1218 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1219 */           draw_raw_segment_c_and_facet_n(ncp, contour, colourArray, contourNormal, inext, len);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1224 */           draw_raw_segment_c_and_edge_n(ncp, contour, colourArray, contourNormal, inext, len);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1235 */         for (j = 0; j < ncp; j++) {
/* 1236 */           back_loop[j] = 
/* 1237 */             matrix.MAT_DOT_VEC_2X3(xformArray[inext], contour[j]);
/*      */           
/* 1239 */           back_loop[j][2] = -len;
/* 1240 */           front_loop[j][2] = 0.0D;
/*      */         } 
/*      */         
/* 1243 */         if (!no_norm) {
/* 1244 */           for (j = 0; j < ncp; j++) {
/* 1245 */             back_norm[j] = 
/* 1246 */               matrix.NORM_XFORM_2X2(xformArray[inext], contourNormal[j]);
/*      */           }
/*      */         }
/*      */ 
/*      */         
/* 1251 */         if (no_cols) {
/* 1252 */           if (no_norm) {
/* 1253 */             draw_segment_plain(ncp, front_loop, back_loop, inext, len);
/*      */ 
/*      */           
/*      */           }
/* 1257 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1258 */             draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len);
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 1264 */             draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1272 */           if ((gleGetJoinStyle() & 0x10) == 16) {
/* 1273 */             nrmv[2] = 1.0D;
/* 1274 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1275 */             draw_front_contour_cap(ncp, front_loop);
/* 1276 */             nrmv[2] = -1.0D;
/* 1277 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1278 */             draw_back_contour_cap(ncp, back_loop);
/*      */           } 
/*      */         } else {
/* 1281 */           if (no_norm) {
/* 1282 */             draw_segment_color(ncp, front_loop, back_loop, colourArray[inext - 1], colourArray[inext], inext, len);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 1289 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 1290 */             draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, colourArray[inext - 1], colourArray[inext], inext, len);
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */ 
/*      */             
/* 1299 */             draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, colourArray[inext - 1], colourArray[inext], inext, len);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1310 */           if ((gleGetJoinStyle() & 0x10) == 16) {
/* 1311 */             GL11.glColor4f(colourArray[inext - 1][0], colourArray[inext - 1][1], colourArray[inext - 1][2], colourArray[inext - 1][3]);
/* 1312 */             nrmv[2] = 1.0D;
/* 1313 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1314 */             draw_front_contour_cap(ncp, front_loop);
/*      */             
/* 1316 */             GL11.glColor4f(colourArray[inext][0], colourArray[inext][1], colourArray[inext][2], colourArray[inext][3]);
/* 1317 */             nrmv[2] = -1.0D;
/* 1318 */             GL11.glNormal3d(nrmv[0], nrmv[1], nrmv[2]);
/* 1319 */             draw_back_contour_cap(ncp, back_loop);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1327 */       GL11.glPopMatrix();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1332 */       tmp = front_loop;
/* 1333 */       front_loop = back_loop;
/* 1334 */       back_loop = tmp;
/* 1335 */       tmp = front_norm;
/* 1336 */       front_norm = back_norm;
/* 1337 */       back_norm = tmp;
/*      */       
/* 1339 */       i = inext;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1344 */       inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, pointArray);
/*      */       
/* 1346 */       len = matrix.VEC_LENGTH(diff);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_plain(int ncp, double[][] contour, int inext, double len) {
/* 1365 */     double[] point = new double[3];
/*      */     
/* 1367 */     System.err.println("draw_raw_segment_plain()");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1372 */     GL11.glBegin(5);
/* 1373 */     for (int j = 0; j < ncp; j++) {
/* 1374 */       point[0] = contour[j][0];
/* 1375 */       point[1] = contour[j][1];
/* 1376 */       point[2] = 0.0D;
/* 1377 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1381 */       point[2] = -len;
/* 1382 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1387 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1391 */       point[0] = contour[0][0];
/* 1392 */       point[1] = contour[0][1];
/* 1393 */       point[2] = 0.0D;
/* 1394 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1398 */       point[2] = -len;
/* 1399 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */     
/* 1403 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1408 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */       
/* 1412 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1417 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_color(int ncp, double[][] contour, float[][] color_array, int inext, double len) {
/* 1434 */     double[] point = new double[3];
/* 1435 */     System.out.println("draw_raw_segment_color");
/*      */ 
/*      */ 
/*      */     
/* 1439 */     GL11.glBegin(5);
/* 1440 */     double tc = 0.0D;
/* 1441 */     for (int j = 0; j < ncp; j++) {
/* 1442 */       tc = j / ncp;
/* 1443 */       point[0] = contour[j][0];
/* 1444 */       point[1] = contour[j][1];
/* 1445 */       point[2] = 0.0D;
/* 1446 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 1447 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1448 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */       
/* 1450 */       point[2] = -len;
/* 1451 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 1452 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1453 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */     
/* 1456 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1460 */       point[0] = contour[0][0];
/* 1461 */       point[1] = contour[0][1];
/* 1462 */       point[2] = 0.0D;
/*      */       
/* 1464 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/*      */       
/* 1466 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1467 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1471 */       point[2] = -len;
/* 1472 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 1473 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1474 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1479 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1489 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 1490 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1491 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1496 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 1497 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1498 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */     
/* 1501 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_edge_n(int ncp, double[][] contour, double[][] cont_normal, int inext, double len) {
/* 1517 */     double[] point = new double[3];
/* 1518 */     double[] norm = new double[3];
/*      */     
/* 1520 */     System.err.println("draw_raw_segment_edge_n");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1525 */     norm[2] = 0.0D;
/* 1526 */     GL11.glBegin(5);
/* 1527 */     for (int j = 0; j < ncp; j++) {
/* 1528 */       norm[0] = cont_normal[j][0];
/* 1529 */       norm[1] = cont_normal[j][1];
/* 1530 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1532 */       point[0] = contour[j][0];
/* 1533 */       point[1] = contour[j][1];
/* 1534 */       point[2] = 0.0D;
/* 1535 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1539 */       point[2] = -len;
/* 1540 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1545 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1549 */       norm[0] = cont_normal[0][0];
/* 1550 */       norm[1] = cont_normal[0][1];
/* 1551 */       norm[2] = 0.0D;
/* 1552 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1554 */       point[0] = contour[0][0];
/* 1555 */       point[1] = contour[0][1];
/* 1556 */       point[2] = 0.0D;
/* 1557 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1561 */       point[2] = -len;
/* 1562 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */     
/* 1566 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1571 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */       
/* 1575 */       norm[1] = 0.0D; norm[0] = 0.0D;
/* 1576 */       norm[2] = 1.0D;
/* 1577 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1578 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1583 */       norm[2] = -1.0D;
/* 1584 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1585 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_c_and_edge_n(int ncp, double[][] contour, float[][] color_array, double[][] cont_normal, int inext, double len) {
/* 1602 */     double[] point = new double[3];
/* 1603 */     double[] norm = new double[3];
/*      */ 
/*      */     
/* 1606 */     System.out.println("draw_raw_segment_c_and_edge_n");
/*      */ 
/*      */ 
/*      */     
/* 1610 */     norm[2] = 0.0D;
/* 1611 */     GL11.glBegin(5);
/* 1612 */     for (int j = 0; j < ncp; j++) {
/* 1613 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */       
/* 1615 */       norm[0] = cont_normal[j][0];
/* 1616 */       norm[1] = cont_normal[j][1];
/* 1617 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1619 */       point[0] = contour[j][0];
/* 1620 */       point[1] = contour[j][1];
/* 1621 */       point[2] = 0.0D;
/* 1622 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1626 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1627 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1629 */       point[2] = -len;
/* 1630 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1636 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1640 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */       
/* 1642 */       norm[0] = cont_normal[0][0];
/* 1643 */       norm[1] = cont_normal[0][1];
/* 1644 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1646 */       point[0] = contour[0][0];
/* 1647 */       point[1] = contour[0][1];
/* 1648 */       point[2] = 0.0D;
/* 1649 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1653 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1654 */       norm[0] = cont_normal[0][0];
/* 1655 */       norm[1] = cont_normal[0][1];
/* 1656 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1658 */       point[2] = -len;
/* 1659 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1664 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1669 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1674 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1675 */       norm[1] = 0.0D; norm[0] = 0.0D;
/* 1676 */       norm[2] = 1.0D;
/* 1677 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1678 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1683 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1684 */       norm[2] = -1.0D;
/* 1685 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1686 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_facet_n(int ncp, double[][] contour, double[][] cont_normal, int inext, double len) {
/* 1702 */     double[] point = new double[3];
/* 1703 */     double[] norm = new double[3];
/* 1704 */     System.out.println("draw_raw_segment_facet_n");
/*      */ 
/*      */ 
/*      */     
/* 1708 */     norm[2] = 0.0D;
/*      */     
/* 1710 */     GL11.glBegin(5);
/* 1711 */     for (int j = 0; j < ncp - 1; j++) {
/*      */ 
/*      */ 
/*      */       
/* 1715 */       norm[0] = cont_normal[j][0];
/* 1716 */       norm[1] = cont_normal[j][1];
/* 1717 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1719 */       point[0] = contour[j][0];
/* 1720 */       point[1] = contour[j][1];
/* 1721 */       point[2] = 0.0D;
/* 1722 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1726 */       point[2] = -len;
/* 1727 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1731 */       point[0] = contour[j + 1][0];
/* 1732 */       point[1] = contour[j + 1][1];
/* 1733 */       point[2] = 0.0D;
/* 1734 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1738 */       point[2] = -len;
/* 1739 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1745 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1749 */       norm[0] = cont_normal[ncp - 1][0];
/* 1750 */       norm[1] = cont_normal[ncp - 1][1];
/* 1751 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1753 */       point[0] = contour[ncp - 1][0];
/* 1754 */       point[1] = contour[ncp - 1][1];
/* 1755 */       point[2] = 0.0D;
/* 1756 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1760 */       point[2] = -len;
/* 1761 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1765 */       point[0] = contour[0][0];
/* 1766 */       point[1] = contour[0][1];
/* 1767 */       point[2] = 0.0D;
/* 1768 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1772 */       point[2] = -len;
/* 1773 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1778 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1783 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1788 */       norm[1] = 0.0D; norm[0] = 0.0D;
/* 1789 */       norm[2] = 1.0D;
/* 1790 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1791 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1796 */       norm[2] = -1.0D;
/* 1797 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1798 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_segment_c_and_facet_n(int ncp, double[][] contour, float[][] color_array, double[][] cont_normal, int inext, double len) {
/* 1815 */     System.out.println("draw_raw_segment_c_and_facet_n");
/* 1816 */     double[] point = new double[3];
/* 1817 */     double[] norm = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1822 */     norm[2] = 0.0D;
/* 1823 */     GL11.glBegin(5);
/* 1824 */     for (int j = 0; j < ncp - 1; j++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1833 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */       
/* 1835 */       norm[0] = cont_normal[j][0];
/* 1836 */       norm[1] = cont_normal[j][1];
/* 1837 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1839 */       point[0] = contour[j][0];
/* 1840 */       point[1] = contour[j][1];
/* 1841 */       point[2] = 0.0D;
/* 1842 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1846 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1847 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1848 */       point[2] = -len;
/* 1849 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1853 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1854 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1856 */       point[0] = contour[j + 1][0];
/* 1857 */       point[1] = contour[j + 1][1];
/* 1858 */       point[2] = 0.0D;
/* 1859 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1863 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1864 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1865 */       point[2] = -len;
/* 1866 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1872 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 1876 */       point[0] = contour[ncp - 1][0];
/* 1877 */       point[1] = contour[ncp - 1][1];
/* 1878 */       point[2] = 0.0D;
/* 1879 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */       
/* 1881 */       norm[0] = cont_normal[ncp - 1][0];
/* 1882 */       norm[1] = cont_normal[ncp - 1][1];
/* 1883 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1884 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1888 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1889 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1891 */       point[2] = -len;
/* 1892 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1896 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */       
/* 1898 */       norm[0] = cont_normal[0][0];
/* 1899 */       norm[1] = cont_normal[0][1];
/* 1900 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1902 */       point[0] = contour[0][0];
/* 1903 */       point[1] = contour[0][1];
/* 1904 */       point[2] = 0.0D;
/* 1905 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */ 
/*      */ 
/*      */       
/* 1909 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1910 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/*      */       
/* 1912 */       point[2] = -len;
/* 1913 */       GL11.glVertex3d(point[0], point[1], point[2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1919 */     GL11.glEnd();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1924 */     if ((gleGetJoinStyle() & 0x10) == 16) {
/*      */ 
/*      */ 
/*      */       
/* 1928 */       GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/* 1929 */       norm[1] = 0.0D; norm[0] = 0.0D;
/* 1930 */       norm[2] = 1.0D;
/* 1931 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1932 */       draw_raw_style_end_cap(ncp, contour, 0.0D, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1937 */       GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/* 1938 */       norm[2] = -1.0D;
/* 1939 */       GL11.glNormal3d(norm[0], norm[1], norm[2]);
/* 1940 */       draw_raw_style_end_cap(ncp, contour, -len, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_raw_style_end_cap(int ncp, double[][] contour, double zval, boolean frontwards) {
/* 1952 */     System.out.println("draw_raw_style_end_cap");
/* 1953 */     GLUtessellator tobj = GLU.gluNewTess();
/* 1954 */     tobj.gluTessProperty(100140, 100130.0D);
/* 1955 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 1956 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 1957 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 1958 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */ 
/*      */     
/* 1961 */     tobj.gluTessBeginPolygon(null);
/* 1962 */     tobj.gluTessBeginContour();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1967 */     if (frontwards) {
/* 1968 */       for (int j = 0; j < ncp; j++) {
/* 1969 */         double[] vertex = new double[3];
/* 1970 */         vertex[0] = contour[j][0];
/* 1971 */         vertex[1] = contour[j][1];
/* 1972 */         vertex[2] = zval;
/*      */         
/* 1974 */         tobj.gluTessVertex(vertex, 0, vertex);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1980 */       for (int j = ncp - 1; j > -1; j--) {
/* 1981 */         double[] vertex = new double[3];
/* 1982 */         vertex[0] = contour[j][0];
/* 1983 */         vertex[1] = contour[j][1];
/* 1984 */         vertex[2] = zval;
/*      */         
/* 1986 */         tobj.gluTessVertex(vertex, 0, vertex);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1991 */     tobj.gluTessEndContour();
/* 1992 */     tobj.gluTessEndPolygon();
/* 1993 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_front_contour_cap(int ncp, double[][] contour) {
/* 2002 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2003 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2004 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2005 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2006 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2007 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */ 
/*      */     
/* 2010 */     tobj.gluTessBeginPolygon(null);
/* 2011 */     tobj.gluTessBeginContour();
/*      */     
/* 2013 */     for (int j = 0; j < ncp; j++)
/*      */     {
/* 2015 */       tobj.gluTessVertex(contour[j], 0, contour[j]);
/*      */     }
/*      */ 
/*      */     
/* 2019 */     tobj.gluTessEndContour();
/* 2020 */     tobj.gluTessEndPolygon();
/* 2021 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_back_contour_cap(int ncp, double[][] contour) {
/* 2031 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2032 */     tobj.gluTessProperty(100140, 100132.0D);
/* 2033 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2034 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2035 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2036 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */ 
/*      */     
/* 2039 */     tobj.gluTessBeginPolygon(null);
/* 2040 */     tobj.gluTessBeginContour();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2051 */     for (int j = ncp - 1; j > -1; j--)
/*      */     {
/* 2053 */       tobj.gluTessVertex(contour[j], 0, contour[j]);
/*      */     }
/*      */ 
/*      */     
/* 2057 */     tobj.gluTessEndContour();
/* 2058 */     tobj.gluTessEndPolygon();
/* 2059 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void extrusion_angle_join(int ncp, double[][] contour, double[][] cont_normal, double[] up, int npoints, double[][] point_array, float[][] color_array, double[][][] xform_array) throws GLEException {
/* 2111 */     int i = 0;
/* 2112 */     int j = 0;
/* 2113 */     int inext = 0;
/* 2114 */     int inextnext = 0;
/* 2115 */     double[][] m = new double[4][4];
/* 2116 */     double len = 0.0D;
/* 2117 */     double len_seg = 0.0D;
/* 2118 */     double[] diff = new double[3];
/* 2119 */     double[] bi_0 = new double[3];
/* 2120 */     double[] bi_1 = new double[3];
/*      */ 
/*      */     
/* 2123 */     double[] bisector_0 = new double[3];
/* 2124 */     double[] bisector_1 = new double[3];
/*      */ 
/*      */     
/* 2127 */     double[] end_point_0 = new double[3];
/* 2128 */     double[] end_point_1 = new double[3];
/* 2129 */     double[] origin = new double[3];
/* 2130 */     double[] neg_z = new double[3];
/* 2131 */     double[] yup = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2150 */     if (up == null) {
/* 2151 */       yup[0] = 0.0D;
/* 2152 */       yup[1] = 1.0D;
/* 2153 */       yup[2] = 0.0D;
/*      */     } else {
/* 2155 */       yup = matrix.VEC_COPY(up);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2161 */     yup = up_sanity_check(yup, npoints, point_array);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2166 */     origin[0] = 0.0D;
/* 2167 */     origin[1] = 0.0D;
/* 2168 */     origin[2] = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2173 */     neg_z[0] = 0.0D;
/* 2174 */     neg_z[1] = 0.0D;
/* 2175 */     neg_z[2] = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2180 */     i = 1;
/* 2181 */     inext = i;
/* 2182 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, len, diff, point_array);
/*      */     
/* 2184 */     len = matrix.VEC_LENGTH(diff);
/* 2185 */     len_seg = len;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2192 */     bi_0 = intersect.bisecting_plane(point_array[0], point_array[1], point_array[inext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2199 */     yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2209 */     double[][] front_loop = new double[ncp][3];
/* 2210 */     double[][] back_loop = new double[ncp][3];
/* 2211 */     double[][] front_norm = new double[ncp][3];
/* 2212 */     double[][] back_norm = new double[ncp][3];
/* 2213 */     double[][] norm_loop = front_norm;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2218 */     if (cont_normal != null) {
/* 2219 */       if (xform_array == null) {
/* 2220 */         for (j = 0; j < ncp; j++) {
/* 2221 */           norm_loop[j][0] = cont_normal[j][0];
/* 2222 */           norm_loop[j][1] = cont_normal[j][1];
/* 2223 */           norm_loop[j][2] = 0.0D;
/*      */         } 
/*      */       } else {
/* 2226 */         for (j = 0; j < ncp; j++) {
/* 2227 */           front_norm[j] = 
/* 2228 */             matrix.NORM_XFORM_2X2(xform_array[inext - 1], cont_normal[j]);
/*      */           
/* 2230 */           front_norm[j][2] = 0.0D;
/* 2231 */           back_norm[j][2] = 0.0D;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 2236 */     boolean first_time = true;
/*      */ 
/*      */ 
/*      */     
/* 2240 */     while (inext < npoints - 1) {
/* 2241 */       inextnext = inext;
/*      */ 
/*      */ 
/*      */       
/* 2245 */       inextnext = intersect.FIND_NON_DEGENERATE_POINT(inextnext, npoints, len, diff, point_array);
/*      */ 
/*      */       
/* 2248 */       len = matrix.VEC_LENGTH(diff);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2253 */       bi_1 = intersect.bisecting_plane(point_array[i], point_array[inext], point_array[inextnext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2261 */       m = matrix.uviewpoint_d(point_array[i], point_array[inext], yup);
/*      */ 
/*      */       
/* 2264 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/*      */       
/* 2266 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2274 */       mbuffer.flip();
/* 2275 */       GL11.glPushMatrix();
/* 2276 */       GL11.glMultMatrix(mbuffer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2282 */       bisector_0 = matrix.MAT_DOT_VEC_3X3(m, bi_0);
/* 2283 */       bisector_1 = matrix.MAT_DOT_VEC_3X3(m, bi_1);
/*      */       
/* 2285 */       neg_z[2] = -len_seg;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2293 */       for (j = 0; j < ncp; j++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2300 */         if (cont_normal != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2306 */           if (xform_array != null)
/*      */           {
/*      */ 
/*      */             
/* 2310 */             back_norm[j] = 
/* 2311 */               matrix.NORM_XFORM_2X2(xform_array[inext], cont_normal[j]);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2327 */           if ((gleGetJoinStyle() & 0x400) == 1024) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2332 */             if (xform_array == null) {
/* 2333 */               back_norm[j][0] = cont_normal[j][0];
/* 2334 */               back_norm[j][1] = cont_normal[j][1];
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2344 */             front_norm[j][2] = 0.0D;
/* 2345 */             front_norm[j] = 
/* 2346 */               matrix.VEC_PERP(front_norm[j], bisector_0);
/* 2347 */             front_norm[j] = 
/* 2348 */               matrix.VEC_NORMALIZE(front_norm[j]);
/*      */             
/* 2350 */             back_norm[j][2] = 0.0D;
/* 2351 */             back_norm[j] = 
/* 2352 */               matrix.VEC_PERP(back_norm[j], bisector_1);
/* 2353 */             back_norm[j] = 
/* 2354 */               matrix.VEC_NORMALIZE(back_norm[j]);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2368 */         if (xform_array == null) {
/* 2369 */           end_point_0[0] = contour[j][0];
/* 2370 */           end_point_0[1] = contour[j][1];
/*      */           
/* 2372 */           end_point_1[0] = contour[j][0];
/* 2373 */           end_point_1[1] = contour[j][1];
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 2379 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext - 1], contour[j]);
/*      */ 
/*      */           
/* 2382 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext - 1], contour[j]);
/*      */         } 
/*      */ 
/*      */         
/* 2386 */         end_point_0[2] = 0.0D;
/* 2387 */         end_point_1[2] = -len_seg;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2393 */         front_loop[j] = 
/* 2394 */           intersect.INNERSECT(origin, bisector_0, end_point_0, end_point_1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2415 */         if (xform_array != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2420 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */ 
/*      */           
/* 2423 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2428 */         end_point_0[2] = 0.0D;
/* 2429 */         end_point_1[2] = -len_seg;
/*      */         
/* 2431 */         back_loop[j] = 
/* 2432 */           intersect.INNERSECT(neg_z, bisector_1, end_point_0, end_point_1);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2458 */       if ((gleGetJoinStyle() & 0x10) == 16) {
/* 2459 */         if (first_time) {
/* 2460 */           if (color_array != null) {
/* 2461 */             GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */           }
/* 2463 */           first_time = false;
/* 2464 */           draw_angle_style_front_cap(ncp, bisector_0, front_loop);
/*      */         } 
/* 2466 */         if (inext == npoints - 2) {
/* 2467 */           if (color_array != null) {
/* 2468 */             GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/*      */           }
/* 2470 */           draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2487 */       if (xform_array == null && (
/* 2488 */         gleGetJoinStyle() & 0x400) != 1024) {
/* 2489 */         if (color_array == null) {
/* 2490 */           if (cont_normal == null) {
/* 2491 */             draw_segment_plain(ncp, front_loop, back_loop, inext, len_seg);
/*      */           
/*      */           }
/* 2494 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2495 */             draw_segment_facet_n(ncp, front_loop, back_loop, norm_loop, inext, len_seg);
/*      */           }
/*      */           else {
/*      */             
/* 2499 */             draw_segment_edge_n(ncp, front_loop, back_loop, norm_loop, inext, len_seg);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 2505 */         else if (cont_normal == null) {
/* 2506 */           draw_segment_color(ncp, front_loop, back_loop, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 2512 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2513 */           draw_segment_c_and_facet_n(ncp, front_loop, back_loop, norm_loop, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 2521 */           draw_segment_c_and_edge_n(ncp, front_loop, back_loop, norm_loop, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2532 */       else if (color_array == null) {
/* 2533 */         if (cont_normal == null) {
/* 2534 */           draw_segment_plain(ncp, front_loop, back_loop, inext, len_seg);
/*      */ 
/*      */         
/*      */         }
/* 2538 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2539 */           draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len_seg);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 2545 */           draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, len_seg);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2553 */       else if (cont_normal == null) {
/* 2554 */         draw_segment_color(ncp, front_loop, back_loop, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2560 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 2561 */         draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2571 */         draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[inext - 1], color_array[inext], inext, len_seg);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2590 */       GL11.glPopMatrix();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2595 */       len_seg = len;
/* 2596 */       i = inext;
/* 2597 */       inext = inextnext;
/* 2598 */       bi_0 = matrix.VEC_COPY(bi_1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2603 */       double[][] tmp = front_norm;
/* 2604 */       front_norm = back_norm;
/* 2605 */       back_norm = tmp;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2610 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_angle_style_front_cap(int ncp, double[] bi, double[][] point_array) {
/* 2620 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2621 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2622 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2623 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2624 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2625 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 2627 */     if (bi[2] < 0.0D) {
/* 2628 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */     
/* 2631 */     GL11.glNormal3d(bi[0], bi[1], bi[2]);
/*      */ 
/*      */     
/* 2634 */     tobj.gluTessBeginPolygon(null);
/* 2635 */     tobj.gluTessBeginContour();
/* 2636 */     for (int j = 0; j < ncp; j++)
/*      */     {
/* 2638 */       tobj.gluTessVertex(point_array[j], 0, point_array[j]);
/*      */     }
/*      */     
/* 2641 */     tobj.gluTessEndContour();
/* 2642 */     tobj.gluTessEndPolygon();
/* 2643 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_angle_style_back_cap(int ncp, double[] bi, double[][] point_array) {
/* 2653 */     GLUtessellator tobj = GLU.gluNewTess();
/* 2654 */     tobj.gluTessProperty(100140, 100130.0D);
/* 2655 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 2656 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 2657 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 2658 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 2660 */     if (bi[2] > 0.0D) {
/* 2661 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */     
/* 2664 */     GL11.glNormal3d(bi[0], bi[1], bi[2]);
/*      */     
/* 2666 */     tobj.gluTessBeginPolygon(null);
/* 2667 */     tobj.gluTessBeginContour();
/* 2668 */     for (int j = ncp - 1; j >= 0; j--)
/*      */     {
/* 2670 */       tobj.gluTessVertex(point_array[j], 0, point_array[j]);
/*      */     }
/*      */     
/* 2673 */     tobj.gluTessEndContour();
/* 2674 */     tobj.gluTessEndPolygon();
/* 2675 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_plain(int ncp, double[][] front_contour, double[][] back_contour, int inext, double len) {
/* 2689 */     System.out.println("draw_segment_plain");
/*      */     
/* 2691 */     GL11.glBegin(5);
/* 2692 */     for (int j = 0; j < ncp; j++) {
/* 2693 */       double tc = j / ncp;
/* 2694 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2695 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2696 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 2697 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2700 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2701 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2702 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2703 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 2704 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2706 */     GL11.glEnd();
/* 2707 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_color(int ncp, double[][] front_contour, double[][] back_contour, float[] color_last, float[] color_next, int inext, double len) {
/* 2720 */     GL11.glBegin(5);
/* 2721 */     double tc = 0.0D;
/* 2722 */     for (int j = 0; j < ncp; j++) {
/* 2723 */       tc = j / ncp;
/* 2724 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2725 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2726 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2727 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 2728 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2729 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2732 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 2736 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2737 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2738 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2739 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 2740 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2741 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/*      */     
/* 2744 */     GL11.glEnd();
/*      */     
/* 2746 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, int inext, double len) {
/* 2754 */     System.out.println("draw_segment_edge_n");
/* 2755 */     GL11.glBegin(5);
/* 2756 */     for (int j = 0; j < ncp; j++) {
/* 2757 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2758 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2759 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2762 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2763 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2764 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2765 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2767 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_c_and_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, float[] color_last, float[] color_next, int inext, double len) {
/* 2778 */     GL11.glBegin(5);
/* 2779 */     double tc = 0.0D;
/* 2780 */     for (int j = 0; j < ncp; j++) {
/* 2781 */       tc = j / ncp;
/* 2782 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2783 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2784 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2785 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2787 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 2788 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2789 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2790 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2793 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2794 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2795 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2796 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2797 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2799 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 2800 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2801 */       GL11.glNormal3d(norm_cont[0][0], norm_cont[0][1], norm_cont[0][2]);
/* 2802 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2804 */     GL11.glEnd();
/* 2805 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, int inext, double len) {
/* 2819 */     GL11.glBegin(5);
/* 2820 */     for (int j = 0; j < ncp - 1; j++) {
/* 2821 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2822 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */ 
/*      */ 
/*      */       
/* 2826 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */ 
/*      */ 
/*      */       
/* 2830 */       GL11.glVertex3d(front_contour[j + 1][0], front_contour[j + 1][1], front_contour[j + 1][2]);
/*      */ 
/*      */ 
/*      */       
/* 2834 */       GL11.glVertex3d(back_contour[j + 1][0], back_contour[j + 1][1], back_contour[j + 1][2]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2840 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 2844 */       GL11.glNormal3d(norm_cont[ncp - 1][0], norm_cont[ncp - 1][1], norm_cont[ncp - 1][2]);
/* 2845 */       GL11.glVertex3d(front_contour[ncp - 1][0], front_contour[ncp - 1][1], front_contour[ncp - 1][2]);
/*      */ 
/*      */ 
/*      */       
/* 2849 */       GL11.glVertex3d(back_contour[ncp - 1][0], back_contour[ncp - 1][1], back_contour[ncp - 1][2]);
/*      */ 
/*      */ 
/*      */       
/* 2853 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */ 
/*      */ 
/*      */       
/* 2857 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/*      */ 
/*      */     
/* 2861 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_segment_c_and_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] norm_cont, float[] color_last, float[] color_next, int inext, double len) {
/* 2872 */     System.out.println("draw_segment_c_and_facet_n");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2883 */     GL11.glBegin(5);
/* 2884 */     for (int j = 0; j < ncp - 1; j++) {
/* 2885 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2886 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2887 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2889 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2890 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2891 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 2893 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2894 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2895 */       GL11.glVertex3d(front_contour[j + 1][0], front_contour[j + 1][1], front_contour[j + 1][2]);
/*      */       
/* 2897 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2898 */       GL11.glNormal3d(norm_cont[j][0], norm_cont[j][1], norm_cont[j][2]);
/* 2899 */       GL11.glVertex3d(back_contour[j + 1][0], back_contour[j + 1][1], back_contour[j + 1][2]);
/*      */     } 
/*      */     
/* 2902 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2903 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2904 */       GL11.glNormal3d(norm_cont[ncp - 1][0], norm_cont[ncp - 1][1], norm_cont[ncp - 1][2]);
/* 2905 */       GL11.glVertex3d(front_contour[ncp - 1][0], front_contour[ncp - 1][1], front_contour[ncp - 1][2]);
/*      */       
/* 2907 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2908 */       GL11.glNormal3d(norm_cont[ncp - 1][0], norm_cont[ncp - 1][1], norm_cont[ncp - 1][2]);
/* 2909 */       GL11.glVertex3d(back_contour[ncp - 1][0], back_contour[ncp - 1][1], back_contour[ncp - 1][2]);
/*      */       
/* 2911 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2912 */       GL11.glNormal3d(norm_cont[ncp - 1][0], norm_cont[ncp - 1][1], norm_cont[ncp - 1][2]);
/* 2913 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2915 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2916 */       GL11.glNormal3d(norm_cont[ncp - 1][0], norm_cont[ncp - 1][1], norm_cont[ncp - 1][2]);
/* 2917 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2919 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_binorm_segment_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, int inext, double len) {
/* 2932 */     GL11.glBegin(5);
/* 2933 */     double tc = 0.0D;
/* 2934 */     for (int j = 0; j < ncp; j++) {
/* 2935 */       tc = j / ncp;
/* 2936 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2937 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2938 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/* 2939 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 2940 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 2941 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2944 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2945 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2946 */       GL11.glNormal3d(front_norm[0][0], front_norm[0][1], front_norm[0][2]);
/* 2947 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/* 2948 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 2949 */       GL11.glNormal3d(back_norm[0][0], back_norm[0][1], back_norm[0][2]);
/* 2950 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2952 */     GL11.glEnd();
/* 2953 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_binorm_segment_c_and_edge_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, float[] color_last, float[] color_next, int inext, double len) {
/* 2965 */     GL11.glBegin(5);
/* 2966 */     double tc = 0.0D;
/* 2967 */     for (int j = 0; j < ncp; j++) {
/*      */       
/* 2969 */       tc = j / ncp;
/*      */       
/* 2971 */       GL11.glTexCoord2d(tc, this.SLICE_PROGRESS);
/* 2972 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2973 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2974 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 2976 */       GL11.glTexCoord2d(tc, (this.SLICE_PROGRESS + this.SLICE));
/* 2977 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2978 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 2979 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */     } 
/*      */     
/* 2982 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 2983 */       GL11.glTexCoord2d(1.0D, this.SLICE_PROGRESS);
/* 2984 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 2985 */       GL11.glNormal3d(front_norm[0][0], front_norm[0][1], front_norm[0][2]);
/* 2986 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 2988 */       GL11.glTexCoord2d(1.0D, (this.SLICE_PROGRESS + this.SLICE));
/* 2989 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 2990 */       GL11.glNormal3d(back_norm[0][0], back_norm[0][1], back_norm[0][2]);
/* 2991 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 2993 */     GL11.glEnd();
/* 2994 */     this.SLICE_PROGRESS += this.SLICE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_binorm_segment_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, int inext, double len) {
/* 3009 */     System.out.println("draw_binorm_segment_facet_n");
/* 3010 */     GL11.glBegin(5);
/* 3011 */     for (int j = 0; j < ncp - 1; j++) {
/* 3012 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3013 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 3015 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3016 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 3018 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3019 */       GL11.glVertex3d(front_contour[j + 1][0], front_contour[j + 1][1], front_contour[j + 1][2]);
/*      */       
/* 3021 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3022 */       GL11.glVertex3d(back_contour[j + 1][0], back_contour[j + 1][1], back_contour[j + 1][2]);
/*      */     } 
/*      */     
/* 3025 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3026 */       GL11.glNormal3d(front_norm[ncp - 1][0], front_norm[ncp - 1][1], front_norm[ncp - 1][2]);
/* 3027 */       GL11.glVertex3d(front_contour[ncp - 1][0], front_contour[ncp - 1][1], front_contour[ncp - 1][2]);
/*      */       
/* 3029 */       GL11.glNormal3d(back_norm[ncp - 1][0], back_norm[ncp - 1][1], back_norm[ncp - 1][2]);
/* 3030 */       GL11.glVertex3d(back_contour[ncp - 1][0], back_contour[ncp - 1][1], back_contour[ncp - 1][2]);
/*      */       
/* 3032 */       GL11.glNormal3d(front_norm[ncp - 1][0], front_norm[ncp - 1][1], front_norm[ncp - 1][2]);
/* 3033 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 3035 */       GL11.glNormal3d(back_norm[ncp - 1][0], back_norm[ncp - 1][1], back_norm[ncp - 1][2]);
/* 3036 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 3038 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_binorm_segment_c_and_facet_n(int ncp, double[][] front_contour, double[][] back_contour, double[][] front_norm, double[][] back_norm, float[] color_last, float[] color_next, int inext, double len) {
/* 3049 */     System.out.println("draw_binorm_segment_c_and_facet_n");
/* 3050 */     GL11.glBegin(5);
/* 3051 */     for (int j = 0; j < ncp - 1; j++) {
/* 3052 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 3053 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3054 */       GL11.glVertex3d(front_contour[j][0], front_contour[j][1], front_contour[j][2]);
/*      */       
/* 3056 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 3057 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3058 */       GL11.glVertex3d(back_contour[j][0], back_contour[j][1], back_contour[j][2]);
/*      */       
/* 3060 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 3061 */       GL11.glNormal3d(front_norm[j][0], front_norm[j][1], front_norm[j][2]);
/* 3062 */       GL11.glVertex3d(front_contour[j + 1][0], front_contour[j + 1][1], front_contour[j + 1][2]);
/*      */       
/* 3064 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 3065 */       GL11.glNormal3d(back_norm[j][0], back_norm[j][1], back_norm[j][2]);
/* 3066 */       GL11.glVertex3d(back_contour[j + 1][0], back_contour[j + 1][1], back_contour[j + 1][2]);
/*      */     } 
/*      */     
/* 3069 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3070 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 3071 */       GL11.glNormal3d(front_norm[ncp - 1][0], front_norm[ncp - 1][1], front_norm[ncp - 1][2]);
/* 3072 */       GL11.glVertex3d(front_contour[ncp - 1][0], front_contour[ncp - 1][1], front_contour[ncp - 1][2]);
/*      */       
/* 3074 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 3075 */       GL11.glNormal3d(back_norm[ncp - 1][0], back_norm[ncp - 1][1], back_norm[ncp - 1][2]);
/* 3076 */       GL11.glVertex3d(back_contour[ncp - 1][0], back_contour[ncp - 1][1], back_contour[ncp - 1][2]);
/*      */       
/* 3078 */       GL11.glColor4f(color_last[0], color_last[1], color_last[2], color_last[3]);
/* 3079 */       GL11.glNormal3d(front_norm[ncp - 1][0], front_norm[ncp - 1][1], front_norm[ncp - 1][2]);
/* 3080 */       GL11.glVertex3d(front_contour[0][0], front_contour[0][1], front_contour[0][2]);
/*      */       
/* 3082 */       GL11.glColor4f(color_next[0], color_next[1], color_next[2], color_next[3]);
/* 3083 */       GL11.glNormal3d(back_norm[ncp - 1][0], back_norm[ncp - 1][1], back_norm[ncp - 1][2]);
/* 3084 */       GL11.glVertex3d(back_contour[0][0], back_contour[0][1], back_contour[0][2]);
/*      */     } 
/* 3086 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void extrusion_round_or_cut_join(int ncp, double[][] contour, double[][] cont_normal, double[] up, int npoints, double[][] point_array, float[][] color_array, double[][][] xform_array) throws GLEException {
/* 3103 */     int i = 0;
/* 3104 */     int j = 0;
/* 3105 */     int inext = 0;
/* 3106 */     int inextnext = 0;
/* 3107 */     double[][] m = new double[4][4];
/* 3108 */     double tube_len = 0.0D;
/* 3109 */     double seg_len = 0.0D;
/* 3110 */     double[] diff = new double[3];
/* 3111 */     double[] bi_0 = new double[3];
/* 3112 */     double[] bi_1 = new double[3];
/* 3113 */     double[] bisector_0 = new double[3];
/* 3114 */     double[] bisector_1 = new double[3];
/* 3115 */     double[] cut_0 = new double[3];
/* 3116 */     double[] cut_1 = new double[3];
/* 3117 */     double[] lcut_0 = new double[3];
/* 3118 */     double[] lcut_1 = new double[3];
/* 3119 */     boolean valid_cut_0 = false;
/* 3120 */     boolean valid_cut_1 = false;
/* 3121 */     double[] end_point_0 = new double[3];
/* 3122 */     double[] end_point_1 = new double[3];
/* 3123 */     double[] torsion_point_0 = new double[3];
/* 3124 */     double[] torsion_point_1 = new double[3];
/* 3125 */     double[] isect_point = new double[3];
/* 3126 */     double[] origin = new double[3];
/* 3127 */     double[] neg_z = new double[3];
/* 3128 */     double[] yup = new double[3];
/* 3129 */     double[][] front_cap = (double[][])null;
/* 3130 */     double[][] back_cap = (double[][])null;
/* 3131 */     double[][] front_loop = (double[][])null;
/* 3132 */     double[][] back_loop = (double[][])null;
/* 3133 */     double[][] front_norm = (double[][])null;
/* 3134 */     double[][] back_norm = (double[][])null;
/* 3135 */     double[][] norm_loop = (double[][])null;
/* 3136 */     double[][] tmp = (double[][])null;
/* 3137 */     boolean[] front_is_trimmed = null;
/* 3138 */     boolean[] back_is_trimmed = null;
/* 3139 */     float[] front_color = null;
/* 3140 */     float[] back_color = null;
/* 3141 */     boolean join_style_is_cut = false;
/* 3142 */     double dot = 0.0D;
/* 3143 */     boolean first_time = true;
/* 3144 */     double[] cut_vec = null;
/* 3145 */     String cap_callback = null;
/* 3146 */     String tmp_cap_callback = null;
/*      */     
/* 3148 */     if ((gleGetJoinStyle() & 0x3) == 3) {
/* 3149 */       join_style_is_cut = true;
/* 3150 */       cap_callback = new String("cut");
/*      */     } else {
/* 3152 */       join_style_is_cut = false;
/* 3153 */       cap_callback = new String("round");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3160 */     if (up == null) {
/* 3161 */       yup[0] = 0.0D;
/* 3162 */       yup[1] = 1.0D;
/* 3163 */       yup[2] = 0.0D;
/*      */     } else {
/* 3165 */       yup = matrix.VEC_COPY(up);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3171 */     yup = up_sanity_check(yup, npoints, point_array);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3176 */     origin[0] = 0.0D;
/* 3177 */     origin[1] = 0.0D;
/* 3178 */     origin[2] = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3183 */     neg_z[0] = 0.0D;
/* 3184 */     neg_z[1] = 0.0D;
/* 3185 */     neg_z[2] = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3190 */     front_norm = new double[ncp][3];
/* 3191 */     back_norm = new double[ncp][3];
/* 3192 */     front_loop = new double[ncp][3];
/* 3193 */     back_loop = new double[ncp][3];
/* 3194 */     front_cap = new double[ncp][3];
/* 3195 */     back_cap = new double[ncp][3];
/* 3196 */     front_is_trimmed = new boolean[ncp];
/* 3197 */     back_is_trimmed = new boolean[ncp];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3206 */     i = 1;
/* 3207 */     inext = i;
/* 3208 */     inext = intersect.FIND_NON_DEGENERATE_POINT(inext, npoints, seg_len, diff, point_array);
/*      */     
/* 3210 */     seg_len = matrix.VEC_LENGTH(diff);
/* 3211 */     tube_len = seg_len;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3218 */     if (cont_normal != null) {
/* 3219 */       if (xform_array == null) {
/* 3220 */         norm_loop = front_norm;
/* 3221 */         back_norm = norm_loop;
/* 3222 */         for (j = 0; j < ncp; j++) {
/* 3223 */           norm_loop[j][0] = cont_normal[j][0];
/* 3224 */           norm_loop[j][1] = cont_normal[j][1];
/* 3225 */           norm_loop[j][2] = 0.0D;
/*      */         } 
/*      */       } else {
/* 3228 */         for (j = 0; j < ncp; j++) {
/* 3229 */           front_norm[j] = 
/* 3230 */             matrix.NORM_XFORM_2X2(xform_array[inext - 1], cont_normal[j]);
/*      */           
/* 3232 */           front_norm[j][2] = 0.0D;
/* 3233 */           back_norm[j][2] = 0.0D;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 3237 */       front_norm = back_norm = norm_loop = (double[][])null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3243 */     bi_0 = intersect.bisecting_plane(point_array[i - 1], point_array[i], point_array[inext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3250 */     valid_cut_0 = intersect.CUTTING_PLANE(cut_0, point_array[i - 1], point_array[i], point_array[inext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3257 */     yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3266 */     while (inext < npoints - 1) {
/*      */       
/* 3268 */       inextnext = inext;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3273 */       inextnext = intersect.FIND_NON_DEGENERATE_POINT(inextnext, npoints, seg_len, diff, point_array);
/*      */ 
/*      */       
/* 3276 */       seg_len = matrix.VEC_LENGTH(diff);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3281 */       bi_1 = intersect.bisecting_plane(point_array[i], point_array[inext], point_array[inextnext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3289 */       valid_cut_1 = intersect.CUTTING_PLANE(cut_1, point_array[i], point_array[inext], point_array[inextnext]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3297 */       m = matrix.uviewpoint_d(point_array[i], point_array[inext], yup);
/* 3298 */       DoubleBuffer mbuffer = BufferUtils.createDoubleBuffer(16);
/* 3299 */       mbuffer.put(new double[] { m[0][0], m[0][1], m[0][2], m[0][3], m[1][0], m[1][1], m[1][2], m[1][3], m[2][0], m[2][1], m[2][2], m[2][3], m[3][0], m[3][1], m[3][2], m[3][3] });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3307 */       mbuffer.flip();
/* 3308 */       GL11.glPushMatrix();
/* 3309 */       GL11.glMultMatrix(mbuffer);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3314 */       lcut_0 = matrix.MAT_DOT_VEC_3X3(m, cut_0);
/* 3315 */       lcut_1 = matrix.MAT_DOT_VEC_3X3(m, cut_1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3320 */       bisector_0 = matrix.MAT_DOT_VEC_3X3(m, bi_0);
/* 3321 */       bisector_1 = matrix.MAT_DOT_VEC_3X3(m, bi_1);
/*      */       
/* 3323 */       neg_z[2] = -tube_len;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3331 */       for (j = 0; j < ncp; j++) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3336 */         if (xform_array == null) {
/* 3337 */           end_point_0 = matrix.VEC_COPY_2(contour[j]);
/* 3338 */           end_point_1 = matrix.VEC_COPY_2(contour[j]);
/* 3339 */           torsion_point_0 = matrix.VEC_COPY_2(contour[j]);
/* 3340 */           torsion_point_1 = matrix.VEC_COPY_2(contour[j]);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 3345 */           end_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext - 1], contour[j]);
/*      */           
/* 3347 */           torsion_point_0 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */           
/* 3349 */           end_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext], contour[j]);
/*      */           
/* 3351 */           torsion_point_1 = matrix.MAT_DOT_VEC_2X3(xform_array[inext - 1], contour[j]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3360 */           if (cont_normal != null)
/*      */           {
/*      */ 
/*      */             
/* 3364 */             back_norm[j] = 
/* 3365 */               matrix.NORM_XFORM_2X2(xform_array[inext], cont_normal[j]);
/*      */           }
/*      */         } 
/*      */         
/* 3369 */         end_point_0[2] = 0.0D;
/* 3370 */         torsion_point_0[2] = 0.0D;
/* 3371 */         end_point_1[2] = -tube_len;
/* 3372 */         torsion_point_1[2] = -tube_len;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3384 */         if (valid_cut_0 && join_style_is_cut) {
/*      */           
/* 3386 */           isect_point = intersect.INNERSECT(origin, lcut_0, end_point_0, end_point_1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3405 */           if (lcut_0[2] < 0.0D) {
/* 3406 */             lcut_0 = matrix.VEC_SCALE(-1.0D, lcut_0);
/*      */           }
/* 3408 */           dot = lcut_0[0] * end_point_0[0];
/* 3409 */           dot += lcut_0[1] * end_point_0[1];
/*      */           
/* 3411 */           front_loop[j] = matrix.VEC_COPY(isect_point);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 3417 */           dot = 1.0D;
/* 3418 */           front_loop[j] = matrix.VEC_COPY(end_point_0);
/*      */         } 
/*      */ 
/*      */         
/* 3422 */         isect_point = intersect.INNERSECT(origin, bisector_0, end_point_0, torsion_point_1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3445 */         if (dot <= 0.0D || isect_point[2] < front_loop[j][2]) {
/*      */ 
/*      */ 
/*      */           
/* 3449 */           front_cap[j] = matrix.VEC_COPY(front_loop[j]);
/* 3450 */           front_loop[j] = matrix.VEC_COPY(isect_point);
/* 3451 */           front_is_trimmed[j] = true;
/*      */         } else {
/* 3453 */           front_is_trimmed[j] = false;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3461 */         if (front_loop[j][2] < -tube_len) {
/* 3462 */           front_loop[j] = matrix.VEC_COPY(end_point_1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3479 */         if (valid_cut_1 && join_style_is_cut) {
/*      */           
/* 3481 */           isect_point = intersect.INNERSECT(neg_z, lcut_1, end_point_1, end_point_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3494 */           if (lcut_1[2] > 0.0D) {
/* 3495 */             lcut_1 = matrix.VEC_SCALE(-1.0D, lcut_1);
/*      */           }
/* 3497 */           dot = lcut_1[0] * end_point_1[0];
/* 3498 */           dot += lcut_1[1] * end_point_1[1];
/*      */           
/* 3500 */           back_loop[j] = matrix.VEC_COPY(isect_point);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 3506 */           dot = 1.0D;
/* 3507 */           back_loop[j] = matrix.VEC_COPY(end_point_1);
/*      */         } 
/*      */ 
/*      */         
/* 3511 */         isect_point = intersect.INNERSECT(neg_z, bisector_1, torsion_point_0, end_point_1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3537 */         if (dot <= 0.0D || isect_point[2] > back_loop[j][2]) {
/* 3538 */           back_cap[j] = matrix.VEC_COPY(back_loop[j]);
/* 3539 */           back_loop[j] = matrix.VEC_COPY(isect_point);
/* 3540 */           back_is_trimmed[j] = true;
/*      */         } else {
/* 3542 */           back_is_trimmed[j] = false;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3550 */         if (back_loop[j][2] > 0.0D) {
/* 3551 */           back_loop[j] = matrix.VEC_COPY(end_point_0);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3574 */       if (xform_array == null) {
/* 3575 */         if (color_array == null) {
/* 3576 */           if (cont_normal == null) {
/* 3577 */             draw_segment_plain(ncp, front_loop, back_loop, inext, seg_len);
/*      */           
/*      */           }
/* 3580 */           else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3581 */             draw_segment_facet_n(ncp, front_loop, back_loop, norm_loop, inext, seg_len);
/*      */           } else {
/*      */             
/* 3584 */             draw_segment_edge_n(ncp, front_loop, back_loop, norm_loop, inext, seg_len);
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 3589 */         else if (cont_normal == null) {
/* 3590 */           draw_segment_color(ncp, front_loop, back_loop, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 3595 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3596 */           draw_segment_c_and_facet_n(ncp, front_loop, back_loop, norm_loop, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 3602 */           draw_segment_c_and_edge_n(ncp, front_loop, back_loop, norm_loop, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 3611 */       else if (color_array == null) {
/* 3612 */         if (cont_normal == null) {
/* 3613 */           draw_segment_plain(ncp, front_loop, back_loop, inext, seg_len);
/*      */         
/*      */         }
/* 3616 */         else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3617 */           draw_binorm_segment_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, seg_len);
/*      */         }
/*      */         else {
/*      */           
/* 3621 */           draw_binorm_segment_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, inext, seg_len);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 3627 */       else if (cont_normal == null) {
/* 3628 */         draw_segment_color(ncp, front_loop, back_loop, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 3633 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 3634 */         draw_binorm_segment_c_and_facet_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 3643 */         draw_binorm_segment_c_and_edge_n(ncp, front_loop, back_loop, front_norm, back_norm, color_array[inext - 1], color_array[inext], inext, seg_len);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3667 */       if (first_time) {
/* 3668 */         first_time = false;
/* 3669 */         tmp_cap_callback = cap_callback;
/* 3670 */         cap_callback = new String("null");
/* 3671 */         if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3672 */           if (color_array != null) {
/* 3673 */             GL11.glColor4f(color_array[inext - 1][0], color_array[inext - 1][1], color_array[inext - 1][2], color_array[inext - 1][3]);
/*      */           }
/* 3675 */           draw_angle_style_front_cap(ncp, bisector_0, front_loop);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3689 */       if (color_array != null) {
/* 3690 */         front_color = color_array[inext - 1];
/* 3691 */         back_color = color_array[inext];
/*      */       } else {
/* 3693 */         front_color = null;
/* 3694 */         back_color = null;
/*      */       } 
/*      */       
/* 3697 */       if (cont_normal == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3702 */         if (valid_cut_0) {
/* 3703 */           cut_vec = lcut_0;
/*      */         } else {
/* 3705 */           cut_vec = null;
/*      */         } 
/* 3707 */         draw_fillets_and_join_plain(ncp, front_loop, front_cap, front_is_trimmed, origin, bisector_0, front_color, back_color, cut_vec, true, cap_callback);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3722 */         if (inext == npoints - 2) {
/* 3723 */           if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3724 */             if (color_array != null) {
/* 3725 */               GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/*      */             }
/*      */             
/* 3728 */             draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */             
/* 3730 */             cap_callback = new String("null");
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 3736 */           cap_callback = tmp_cap_callback;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3746 */         if (valid_cut_1) {
/* 3747 */           cut_vec = lcut_1;
/*      */         } else {
/* 3749 */           cut_vec = null;
/*      */         } 
/* 3751 */         draw_fillets_and_join_plain(ncp, back_loop, back_cap, back_is_trimmed, neg_z, bisector_1, back_color, front_color, cut_vec, false, cap_callback);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3768 */         if (valid_cut_0) {
/* 3769 */           cut_vec = lcut_0;
/*      */         } else {
/* 3771 */           cut_vec = null;
/*      */         } 
/* 3773 */         draw_fillets_and_join_n_norms(ncp, front_loop, front_cap, front_is_trimmed, origin, bisector_0, front_norm, front_color, back_color, cut_vec, true, cap_callback);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3789 */         if (inext == npoints - 2) {
/* 3790 */           if ((gleGetJoinStyle() & 0x10) == 1) {
/* 3791 */             if (color_array != null) {
/* 3792 */               GL11.glColor4f(color_array[inext][0], color_array[inext][1], color_array[inext][2], color_array[inext][3]);
/*      */             }
/* 3794 */             draw_angle_style_back_cap(ncp, bisector_1, back_loop);
/*      */             
/* 3796 */             cap_callback = new String("null");
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 3802 */           cap_callback = tmp_cap_callback;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3812 */         if (valid_cut_1) {
/* 3813 */           cut_vec = lcut_1;
/*      */         } else {
/* 3815 */           cut_vec = null;
/*      */         } 
/* 3817 */         draw_fillets_and_join_n_norms(ncp, back_loop, back_cap, back_is_trimmed, neg_z, bisector_1, back_norm, back_color, front_color, cut_vec, false, cap_callback);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3838 */       GL11.glPopMatrix();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3843 */       tmp = front_norm;
/* 3844 */       front_norm = back_norm;
/* 3845 */       back_norm = tmp;
/*      */       
/* 3847 */       tube_len = seg_len;
/* 3848 */       i = inext;
/* 3849 */       inext = inextnext;
/* 3850 */       bi_0 = matrix.VEC_COPY(bi_1);
/* 3851 */       cut_0 = matrix.VEC_COPY(cut_1);
/* 3852 */       valid_cut_0 = valid_cut_1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3857 */       yup = matrix.VEC_REFLECT(yup, bi_0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_fillets_and_join_plain(int ncp, double[][] trimmed_loop, double[][] untrimmed_loop, boolean[] is_trimmed, double[] bis_origin, double[] bis_vector, float[] front_color, float[] back_color, double[] cut_vector, boolean face, String cap_callback) {
/* 3875 */     int istop = 0;
/* 3876 */     int icnt = 0;
/* 3877 */     int icnt_prev = 0;
/* 3878 */     int iloop = 0;
/* 3879 */     double[][] cap_loop = (double[][])null;
/* 3880 */     double[] sect = new double[3];
/* 3881 */     double[] tmp_vec = new double[3];
/* 3882 */     int save_style = 0;
/* 3883 */     boolean was_trimmed = false;
/*      */     
/* 3885 */     cap_loop = new double[ncp + 3][3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3893 */     icnt = 0;
/* 3894 */     iloop = 0;
/* 3895 */     if (!is_trimmed[0]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3903 */       if ((gleGetJoinStyle() & 0x3) == 3 && (save_style & 0x1000) != 4096) {
/* 3904 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[0], bis_vector);
/* 3905 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[0], tmp_vec);
/*      */ 
/*      */ 
/*      */         
/* 3909 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 3910 */         iloop++;
/*      */       } 
/* 3912 */       cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[0]);
/* 3913 */       iloop++;
/* 3914 */       icnt_prev = icnt;
/* 3915 */       icnt++;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 3920 */       was_trimmed = true;
/* 3921 */       while (is_trimmed[icnt]) {
/* 3922 */         icnt_prev = icnt;
/* 3923 */         icnt++;
/* 3924 */         if (icnt >= ncp) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3937 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 3938 */       istop = ncp;
/*      */     } else {
/* 3940 */       istop = ncp - 1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3947 */     save_style = gleGetJoinStyle();
/* 3948 */     gleSetJoinStyle(save_style & 0xFFFFEFFF);
/*      */     
/* 3950 */     for (; icnt_prev < istop; icnt_prev++, icnt = ++icnt % ncp) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3963 */       if (!is_trimmed[icnt_prev] || is_trimmed[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3969 */       if (is_trimmed[icnt_prev] && !is_trimmed[icnt]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3980 */         sect = intersect.INNERSECT(bis_origin, bis_vector, untrimmed_loop[icnt_prev], trimmed_loop[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3988 */         draw_fillet_triangle_plain(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3995 */         cap_loop[iloop] = 
/* 3996 */           matrix.VEC_COPY(sect);
/* 3997 */         iloop++;
/* 3998 */         cap_loop[iloop] = 
/* 3999 */           matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4000 */         iloop++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4005 */       if (!is_trimmed[icnt_prev] && !is_trimmed[icnt]) {
/* 4006 */         cap_loop[iloop] = 
/* 4007 */           matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4008 */         iloop++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4014 */       if (!is_trimmed[icnt_prev] && is_trimmed[icnt]) {
/* 4015 */         was_trimmed = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4027 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt_prev], untrimmed_loop[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4035 */         draw_fillet_triangle_plain(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4042 */         cap_loop[iloop] = 
/* 4043 */           matrix.VEC_COPY(sect);
/* 4044 */         iloop++;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4049 */         if (iloop >= 3) {
/* 4050 */           if (cap_callback.equals("cut")) {
/* 4051 */             draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 4059 */           else if (cap_callback.equals("round")) {
/* 4060 */             draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4073 */         iloop = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4083 */     icnt--;
/*      */ 
/*      */     
/* 4086 */     icnt += ncp;
/* 4087 */     icnt %= ncp;
/* 4088 */     if (!is_trimmed[icnt] && iloop >= 2) {
/*      */       
/* 4090 */       tmp_vec = matrix.VEC_SUM(trimmed_loop[icnt], bis_vector);
/* 4091 */       sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt], tmp_vec);
/*      */ 
/*      */ 
/*      */       
/* 4095 */       cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4096 */       iloop++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4103 */       if (!was_trimmed) {
/* 4104 */         gleSetJoinStyle(save_style);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4110 */       if (cap_callback.equals("cut")) {
/* 4111 */         draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 4119 */       else if (cap_callback.equals("round")) {
/* 4120 */         draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, (double[][])null, face);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4134 */     gleSetJoinStyle(save_style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_fillets_and_join_n_norms(int ncp, double[][] trimmed_loop, double[][] untrimmed_loop, boolean[] is_trimmed, double[] bis_origin, double[] bis_vector, double[][] normals, float[] front_color, float[] back_color, double[] cut_vector, boolean face, String cap_callback) {
/* 4150 */     int istop = 0;
/* 4151 */     int icnt = 0;
/* 4152 */     int icnt_prev = 0;
/* 4153 */     int iloop = 0;
/* 4154 */     double[][] cap_loop = (double[][])null;
/* 4155 */     double[][] norm_loop = (double[][])null;
/* 4156 */     double[] sect = new double[3];
/* 4157 */     double[] tmp_vec = new double[3];
/* 4158 */     int save_style = 0;
/* 4159 */     boolean was_trimmed = false;
/*      */     
/* 4161 */     cap_loop = new double[ncp + 3][3];
/* 4162 */     norm_loop = new double[ncp + 3][3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4168 */     icnt = 0;
/* 4169 */     iloop = 0;
/* 4170 */     if (!is_trimmed[0]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4178 */       if ((gleGetJoinStyle() & 0x3) == 3 && (save_style & 0x1000) != 4096) {
/*      */         
/* 4180 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[0], bis_vector);
/* 4181 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[0], tmp_vec);
/*      */ 
/*      */ 
/*      */         
/* 4185 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4186 */         norm_loop[iloop] = matrix.VEC_COPY(normals[0]);
/* 4187 */         iloop++;
/*      */       } 
/* 4189 */       cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[0]);
/* 4190 */       norm_loop[iloop] = matrix.VEC_COPY(normals[0]);
/* 4191 */       iloop++;
/* 4192 */       icnt_prev = icnt;
/* 4193 */       icnt++;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 4198 */       was_trimmed = true;
/* 4199 */       while (is_trimmed[icnt]) {
/* 4200 */         icnt_prev = icnt;
/* 4201 */         icnt++;
/* 4202 */         if (icnt >= ncp) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4215 */     if ((gleGetJoinStyle() & 0x1000) == 4096) {
/* 4216 */       istop = ncp;
/*      */     } else {
/* 4218 */       istop = ncp - 1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4225 */     save_style = gleGetJoinStyle();
/* 4226 */     gleSetJoinStyle(save_style & 0xFFFFEFFF);
/*      */     
/* 4228 */     for (; icnt_prev < istop; icnt_prev++, icnt = ++icnt % ncp) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4242 */       if (!is_trimmed[icnt_prev] || is_trimmed[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4248 */       if (is_trimmed[icnt_prev] && !is_trimmed[icnt]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4259 */         sect = intersect.INNERSECT(bis_origin, bis_vector, untrimmed_loop[icnt_prev], trimmed_loop[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4267 */         draw_fillet_triangle_n_norms(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color, normals[icnt_prev], normals[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4275 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4276 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt_prev]);
/* 4277 */         iloop++;
/* 4278 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4279 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4280 */         iloop++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4286 */       if (!is_trimmed[icnt_prev] && !is_trimmed[icnt]) {
/* 4287 */         cap_loop[iloop] = matrix.VEC_COPY(trimmed_loop[icnt]);
/* 4288 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4289 */         iloop++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4295 */       if (!is_trimmed[icnt_prev] && is_trimmed[icnt]) {
/* 4296 */         was_trimmed = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4307 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt_prev], untrimmed_loop[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4315 */         draw_fillet_triangle_n_norms(trimmed_loop[icnt_prev], trimmed_loop[icnt], sect, face, front_color, back_color, normals[icnt_prev], normals[icnt]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4324 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4330 */         if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4331 */           norm_loop[iloop] = matrix.VEC_COPY(normals[icnt_prev]);
/*      */         } else {
/* 4333 */           norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/*      */         } 
/* 4335 */         iloop++;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4340 */         if (iloop >= 3) {
/* 4341 */           if (cap_callback.equals("cut")) {
/* 4342 */             draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 4350 */           else if (cap_callback.equals("round")) {
/* 4351 */             draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4365 */         iloop = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4373 */     icnt--;
/*      */ 
/*      */     
/* 4376 */     icnt += ncp;
/* 4377 */     icnt %= ncp;
/* 4378 */     if (!is_trimmed[icnt] && iloop >= 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4386 */       if ((gleGetJoinStyle() & 0x3) == 3 && (save_style & 0x1000) != 4096) {
/*      */         
/* 4388 */         tmp_vec = matrix.VEC_SUM(trimmed_loop[icnt], bis_vector);
/* 4389 */         sect = intersect.INNERSECT(bis_origin, bis_vector, trimmed_loop[icnt], tmp_vec);
/*      */ 
/*      */ 
/*      */         
/* 4393 */         cap_loop[iloop] = matrix.VEC_COPY(sect);
/* 4394 */         norm_loop[iloop] = matrix.VEC_COPY(normals[icnt]);
/* 4395 */         iloop++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4403 */       if (!was_trimmed) {
/* 4404 */         gleSetJoinStyle(save_style);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4410 */       if (cap_callback.equals("cut")) {
/* 4411 */         draw_cut_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 4419 */       else if (cap_callback.equals("round")) {
/* 4420 */         draw_round_style_cap_callback(iloop, cap_loop, front_color, cut_vector, bis_vector, norm_loop, face);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4434 */     gleSetJoinStyle(save_style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void draw_fillet_triangle_plain(double[] va, double[] vb, double[] vc, boolean face, float[] front_color, float[] back_color) {
/* 4459 */     if (front_color != null) {
/* 4460 */       GL11.glColor4f(front_color[0], front_color[1], front_color[2], front_color[3]);
/*      */     }
/*      */     
/* 4463 */     GL11.glBegin(5);
/* 4464 */     if (face) {
/* 4465 */       GL11.glVertex3d(va[0], va[1], va[2]);
/* 4466 */       GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */     } else {
/* 4468 */       GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4469 */       GL11.glVertex3d(va[0], va[1], va[2]);
/*      */     } 
/* 4471 */     GL11.glVertex3d(vc[0], vc[1], vc[2]);
/* 4472 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_fillet_triangle_n_norms(double[] va, double[] vb, double[] vc, boolean face, float[] front_color, float[] back_color, double[] na, double[] nb) {
/* 4500 */     if (front_color != null) {
/* 4501 */       GL11.glColor4f(front_color[0], front_color[1], front_color[2], front_color[3]);
/*      */     }
/*      */     
/* 4504 */     GL11.glBegin(5);
/* 4505 */     if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4506 */       GL11.glNormal3d(na[0], na[1], na[2]);
/* 4507 */       if (face) {
/* 4508 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4509 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */       } else {
/* 4511 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4512 */         GL11.glVertex3d(va[0], va[1], va[2]);
/*      */       } 
/* 4514 */       GL11.glNormal3d(vc[0], vc[1], vc[2]);
/*      */     } else {
/* 4516 */       if (face) {
/* 4517 */         GL11.glNormal3d(na[0], na[1], na[2]);
/* 4518 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4519 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/* 4520 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/*      */       } else {
/* 4522 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/* 4523 */         GL11.glVertex3d(vb[0], vb[1], vb[2]);
/* 4524 */         GL11.glNormal3d(na[0], na[1], na[2]);
/* 4525 */         GL11.glVertex3d(va[0], va[1], va[2]);
/* 4526 */         GL11.glNormal3d(nb[0], nb[1], nb[2]);
/*      */       } 
/* 4528 */       GL11.glVertex3d(vc[0], vc[1], vc[2]);
/*      */     } 
/* 4530 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_cut_style_cap_callback(int iloop, double[][] cap, float[] face_color, double[] cut_vector, double[] bisect_vector, double[][] norms, boolean frontwards) {
/* 4548 */     double[] previous_vertex = null;
/* 4549 */     double[] first_vertex = null;
/* 4550 */     boolean is_colinear = false;
/*      */     
/* 4552 */     GLUtessellator tobj = GLU.gluNewTess();
/* 4553 */     tobj.gluTessProperty(100140, 100130.0D);
/* 4554 */     tobj.gluTessCallback(100101, this.tessCallback);
/* 4555 */     tobj.gluTessCallback(100100, this.tessCallback);
/* 4556 */     tobj.gluTessCallback(100102, this.tessCallback);
/* 4557 */     tobj.gluTessCallback(100103, this.tessCallback);
/*      */     
/* 4559 */     if (face_color != null) {
/* 4560 */       GL11.glColor4f(face_color[0], face_color[1], face_color[2], face_color[3]);
/*      */     }
/*      */     
/* 4563 */     if (frontwards) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4568 */       if (cut_vector != null) {
/*      */ 
/*      */ 
/*      */         
/* 4572 */         if (cut_vector[2] < 0.0D) {
/* 4573 */           cut_vector = matrix.VEC_SCALE(-1.0D, cut_vector);
/*      */         }
/* 4575 */         GL11.glNormal3d(cut_vector[0], cut_vector[1], cut_vector[2]);
/*      */       } 
/*      */       
/* 4578 */       tobj.gluTessBeginPolygon(null);
/* 4579 */       tobj.gluTessBeginContour();
/*      */       
/* 4581 */       first_vertex = null;
/* 4582 */       previous_vertex = cap[iloop - 1];
/* 4583 */       for (int i = 0; i < iloop - 1; i++) {
/* 4584 */         is_colinear = intersect.COLINEAR(previous_vertex, cap[i], cap[i + 1]);
/*      */         
/* 4586 */         if (!is_colinear) {
/*      */           
/* 4588 */           tobj.gluTessVertex(cap[i], 0, cap[i]);
/* 4589 */           previous_vertex = cap[i];
/* 4590 */           if (first_vertex == null) {
/* 4591 */             first_vertex = previous_vertex;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 4596 */       if (first_vertex == null) {
/* 4597 */         first_vertex = cap[0];
/*      */       }
/* 4599 */       is_colinear = intersect.COLINEAR(previous_vertex, cap[iloop - 1], first_vertex);
/*      */ 
/*      */       
/* 4602 */       if (!is_colinear)
/*      */       {
/* 4604 */         tobj.gluTessVertex(cap[iloop - 1], 0, cap[iloop - 1]);
/*      */       }
/*      */ 
/*      */       
/* 4608 */       tobj.gluTessEndContour();
/* 4609 */       tobj.gluTessEndPolygon();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 4614 */       if (cut_vector != null) {
/*      */ 
/*      */ 
/*      */         
/* 4618 */         if (cut_vector[2] > 0.0D) {
/* 4619 */           cut_vector = matrix.VEC_SCALE(-1.0D, cut_vector);
/*      */         }
/* 4621 */         GL11.glNormal3d(cut_vector[0], cut_vector[1], cut_vector[2]);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4627 */       tobj.gluTessBeginPolygon(null);
/* 4628 */       tobj.gluTessBeginContour();
/*      */       
/* 4630 */       first_vertex = null;
/* 4631 */       previous_vertex = cap[0];
/* 4632 */       for (int i = iloop - 1; i > 0; i--) {
/* 4633 */         is_colinear = intersect.COLINEAR(previous_vertex, cap[i], cap[i - 1]);
/*      */         
/* 4635 */         if (!is_colinear) {
/*      */           
/* 4637 */           tobj.gluTessVertex(cap[i], 0, cap[i]);
/* 4638 */           previous_vertex = cap[i];
/* 4639 */           if (first_vertex == null) {
/* 4640 */             first_vertex = previous_vertex;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 4645 */       if (first_vertex == null) {
/* 4646 */         first_vertex = cap[iloop - 1];
/*      */       }
/* 4648 */       is_colinear = intersect.COLINEAR(previous_vertex, cap[0], first_vertex);
/*      */       
/* 4650 */       if (!is_colinear)
/*      */       {
/* 4652 */         tobj.gluTessVertex(cap[0], 0, cap[0]);
/*      */       }
/*      */ 
/*      */       
/* 4656 */       tobj.gluTessEndContour();
/* 4657 */       tobj.gluTessEndPolygon();
/*      */     } 
/*      */     
/* 4660 */     tobj.gluDeleteTess();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void draw_round_style_cap_callback(int ncp, double[][] cap, float[] face_color, double[] cut, double[] bi, double[][] norms, boolean frontwards) {
/* 4677 */     double[] axis = new double[3];
/* 4678 */     double[] xycut = new double[3];
/* 4679 */     double theta = 0.0D;
/* 4680 */     double[][] last_contour = (double[][])null;
/* 4681 */     double[][] next_contour = (double[][])null;
/* 4682 */     double[][] last_norm = (double[][])null;
/* 4683 */     double[][] next_norm = (double[][])null;
/* 4684 */     double[] cap_z = null;
/* 4685 */     double[][] tmp = (double[][])null;
/* 4686 */     int i = 0;
/* 4687 */     int j = 0;
/* 4688 */     int k = 0;
/* 4689 */     double[][] m = new double[4][4];
/*      */     
/* 4691 */     if (face_color != null) {
/* 4692 */       GL11.glColor4f(face_color[0], face_color[1], face_color[2], face_color[3]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4702 */     if (cut == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4709 */     if (cut[2] > 0.0D) {
/* 4710 */       cut = matrix.VEC_SCALE(-1.0D, cut);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4716 */     if (bi[2] < 0.0D) {
/* 4717 */       bi = matrix.VEC_SCALE(-1.0D, bi);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4724 */     axis = matrix.VEC_CROSS_PRODUCT(cut, bi);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4730 */     if (!frontwards) {
/* 4731 */       cut = matrix.VEC_SCALE(-1.0D, cut);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4738 */     xycut[0] = 0.0D;
/* 4739 */     xycut[1] = 0.0D;
/* 4740 */     xycut[2] = 1.0D;
/* 4741 */     xycut = matrix.VEC_PERP(cut, xycut);
/* 4742 */     xycut = matrix.VEC_NORMALIZE(xycut);
/* 4743 */     theta = matrix.VEC_DOT_PRODUCT(xycut, cut);
/*      */     
/* 4745 */     theta = Math.acos(theta);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4750 */     theta /= this.__ROUND_TESS_PIECES;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4755 */     m = matrix.urot_axis_d(theta, axis);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4766 */     last_contour = new double[ncp][3];
/* 4767 */     next_contour = new double[ncp][3];
/* 4768 */     cap_z = new double[ncp];
/* 4769 */     last_norm = new double[ncp][3];
/* 4770 */     next_norm = new double[ncp][3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4775 */     if (frontwards) {
/* 4776 */       for (j = 0; j < ncp; j++) {
/* 4777 */         last_contour[j][0] = cap[j][0];
/* 4778 */         last_contour[j][1] = cap[j][1];
/* 4779 */         cap_z[j] = cap[j][2]; last_contour[j][2] = cap[j][2];
/*      */       } 
/*      */       
/* 4782 */       if (norms != null) {
/* 4783 */         for (j = 0; j < ncp; j++) {
/* 4784 */           last_norm[j] = matrix.VEC_COPY(norms[j]);
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 4795 */       for (j = 0; j < ncp; j++) {
/* 4796 */         k = ncp - j - 1;
/* 4797 */         last_contour[k][0] = cap[j][0];
/* 4798 */         last_contour[k][1] = cap[j][1];
/* 4799 */         cap_z[k] = cap[j][2]; last_contour[k][2] = cap[j][2];
/*      */       } 
/*      */       
/* 4802 */       if (norms != null) {
/* 4803 */         if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4804 */           for (j = 0; j < ncp - 1; j++) {
/* 4805 */             k = ncp - j - 2;
/* 4806 */             last_norm[k] = matrix.VEC_COPY(norms[j]);
/*      */           } 
/*      */         } else {
/* 4809 */           for (j = 0; j < ncp; j++) {
/* 4810 */             k = ncp - j - 1;
/* 4811 */             last_norm[k] = matrix.VEC_COPY(norms[j]);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4821 */     for (i = 0; i < this.__ROUND_TESS_PIECES; i++) {
/* 4822 */       for (j = 0; j < ncp; j++) {
/* 4823 */         next_contour[j][2] = next_contour[j][2] - cap_z[j];
/* 4824 */         last_contour[j][2] = last_contour[j][2] - cap_z[j];
/* 4825 */         next_contour[j] = matrix.MAT_DOT_VEC_3X3(m, last_contour[j]);
/* 4826 */         next_contour[j][2] = next_contour[j][2] + cap_z[j];
/* 4827 */         last_contour[j][2] = last_contour[j][2] + cap_z[j];
/*      */       } 
/*      */       
/* 4830 */       if (norms != null) {
/* 4831 */         for (j = 0; j < ncp; j++) {
/* 4832 */           next_norm[j] = matrix.MAT_DOT_VEC_3X3(m, last_norm[j]);
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4839 */       if (norms == null) {
/* 4840 */         draw_segment_plain(ncp, next_contour, last_contour, 0, 0.0D);
/*      */       
/*      */       }
/* 4843 */       else if ((gleGetJoinStyle() & 0x100) == 256) {
/* 4844 */         draw_binorm_segment_facet_n(ncp, next_contour, last_contour, next_norm, last_norm, 0, 0.0D);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 4850 */         draw_binorm_segment_edge_n(ncp, next_contour, last_contour, next_norm, last_norm, 0, 0.0D);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4859 */       tmp = next_contour;
/* 4860 */       next_contour = last_contour;
/* 4861 */       last_contour = tmp;
/*      */       
/* 4863 */       tmp = next_norm;
/* 4864 */       next_norm = last_norm;
/* 4865 */       last_norm = tmp;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class tessellCallBack
/*      */     implements GLUtessellatorCallback
/*      */   {
/*      */     public tessellCallBack(GLU glu) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4889 */     public void begin(int type) { GL11.glBegin(type); }
/*      */ 
/*      */ 
/*      */     
/* 4893 */     public void end() { GL11.glEnd(); }
/*      */ 
/*      */ 
/*      */     
/*      */     public void vertex(Object vertexData) {
/* 4898 */       if (vertexData instanceof double[]) {
/* 4899 */         double[] pointer = (double[])vertexData;
/* 4900 */         if (pointer.length == 6) {
/* 4901 */           GL11.glColor3d(pointer[3], pointer[4], pointer[5]);
/*      */         }
/* 4903 */         GL11.glVertex3d(pointer[0], pointer[1], pointer[2]);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void vertexData(Object vertexData, Object polygonData) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void combine(double[] coords, Object[] data, float[] weight, Object[] outData) {
/* 4919 */       double[] vertex = new double[6];
/*      */ 
/*      */       
/* 4922 */       vertex[0] = coords[0];
/* 4923 */       vertex[1] = coords[1];
/* 4924 */       vertex[2] = coords[2];
/* 4925 */       for (int i = 3; i < 6; 
/*      */         
/* 4927 */         i++) {
/* 4928 */         vertex[i] = weight[0] * (double[])data[0][i] + weight[1] * (double[])data[1][i] + weight[2] * (double[])data[2][i] + weight[3] * (double[])data[3][i];
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4934 */       outData[0] = vertex;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void combineData(double[] coords, Object[] data, float[] weight, Object[] outData, Object polygonData) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void error(int errnum) {
/* 4944 */       String estring = GLU.gluErrorString(errnum);
/* 4945 */       System.err.println("Tessellation Error: " + estring);
/*      */     }
/*      */     
/*      */     public void beginData(int type, Object polygonData) {}
/*      */     
/*      */     public void endData(Object polygonData) {}
/*      */     
/*      */     public void edgeFlag(boolean boundaryEdge) {}
/*      */     
/*      */     public void edgeFlagData(boolean boundaryEdge, Object polygonData) {}
/*      */     
/*      */     public void errorData(int errnum, Object polygonData) {}
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\com\sasmaster\glelwjgl\java\CoreGLE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */