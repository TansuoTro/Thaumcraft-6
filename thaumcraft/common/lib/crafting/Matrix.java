/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import thaumcraft.api.crafting.Part;
/*    */ 
/*    */ public class Matrix {
/*    */   int rows;
/*    */   int cols;
/*    */   Part[][] matrix;
/*    */   
/*    */   public Matrix(Part[][] matrix) {
/* 11 */     this.rows = matrix.length;
/* 12 */     this.cols = matrix[0].length;
/* 13 */     this.matrix = new Part[this.rows][this.cols];
/* 14 */     for (int i = 0; i < this.rows; i++) {
/*    */       
/* 16 */       for (int j = 0; j < this.cols; j++)
/*    */       {
/* 18 */         this.matrix[i][j] = matrix[i][j];
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void Rotate90DegRight(int times) {
/* 25 */     for (int a = 0; a < times; a++) {
/*    */ 
/*    */       
/* 28 */       Part[][] newMatrix = new Part[this.cols][this.rows];
/*    */       
/* 30 */       for (int i = 0; i < this.rows; i++) {
/*    */         
/* 32 */         for (int j = 0; j < this.cols; j++)
/*    */         {
/* 34 */           newMatrix[j][this.rows - i - 1] = this.matrix[i][j];
/*    */         }
/*    */       } 
/*    */       
/* 38 */       this.matrix = newMatrix;
/*    */ 
/*    */       
/* 41 */       int tmp = this.rows;
/* 42 */       this.rows = this.cols;
/* 43 */       this.cols = tmp;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 48 */   public Part[][] getMatrix() { return this.matrix; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\Matrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */