/*    */ package thaumcraft.client.lib.obj;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MeshPart {
/*    */   public String name;
/*    */   public Material material;
/*    */   public List<int[]> indices;
/* 10 */   public int tintIndex = -1;
/*    */ 
/*    */   
/* 13 */   public MeshPart() { this.indices = new ArrayList(); }
/*    */ 
/*    */   
/*    */   public MeshPart(MeshPart p, int ti) {
/* 17 */     this.indices = new ArrayList();
/* 18 */     this.name = p.name;
/* 19 */     this.material = p.material;
/* 20 */     this.indices = p.indices;
/* 21 */     this.tintIndex = ti;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTriangleFace(int[] a, int[] b, int[] c) {
/* 26 */     this.indices.add(a);
/* 27 */     this.indices.add(b);
/* 28 */     this.indices.add(c);
/* 29 */     this.indices.add(c);
/*    */   }
/*    */   
/*    */   public void addQuadFace(int[] a, int[] b, int[] c, int[] d) {
/* 33 */     this.indices.add(a);
/* 34 */     this.indices.add(b);
/* 35 */     this.indices.add(c);
/* 36 */     this.indices.add(d);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\MeshPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */