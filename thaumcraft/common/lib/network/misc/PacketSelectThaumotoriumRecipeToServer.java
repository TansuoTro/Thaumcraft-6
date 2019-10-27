/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.crafting.CrucibleRecipe;
/*    */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*    */ 
/*    */ public class PacketSelectThaumotoriumRecipeToServer
/*    */   extends Object implements IMessage, IMessageHandler<PacketSelectThaumotoriumRecipeToServer, IMessage> {
/*    */   private long pos;
/*    */   private int hash;
/*    */   
/*    */   public PacketSelectThaumotoriumRecipeToServer() {}
/*    */   
/*    */   public PacketSelectThaumotoriumRecipeToServer(EntityPlayer player, BlockPos pos, int recipeHash) {
/* 24 */     this.pos = pos.func_177986_g();
/* 25 */     this.hash = recipeHash;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 30 */     buffer.writeLong(this.pos);
/* 31 */     buffer.writeInt(this.hash);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 36 */     this.pos = buffer.readLong();
/* 37 */     this.hash = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketSelectThaumotoriumRecipeToServer message, final MessageContext ctx) {
/* 42 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 43 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 45 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 46 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/* 47 */             BlockPos bp = BlockPos.func_177969_a(message.pos);
/* 48 */             if (worldServer != null && entityPlayerMP != null && entityPlayerMP instanceof EntityPlayer && bp != null) {
/* 49 */               TileEntity te = worldServer.func_175625_s(bp);
/* 50 */               if (te != null && te instanceof TileThaumatorium) {
/* 51 */                 TileThaumatorium thaumatorium = (TileThaumatorium)te;
/*    */                 
/* 53 */                 int i = 0;
/* 54 */                 boolean flag = false;
/*    */                 Iterator iterator;
/* 56 */                 for (iterator = thaumatorium.recipeHash.iterator(); iterator.hasNext(); ) { int hash = ((Integer)iterator.next()).intValue();
/* 57 */                   if (message.hash == hash) {
/* 58 */                     thaumatorium.recipeEssentia.remove(i);
/* 59 */                     thaumatorium.recipePlayer.remove(i);
/* 60 */                     thaumatorium.recipeHash.remove(i);
/* 61 */                     thaumatorium.currentCraft = -1;
/* 62 */                     flag = true;
/*    */                     break;
/*    */                   } 
/* 65 */                   i++; }
/*    */ 
/*    */                 
/* 68 */                 if (!flag && thaumatorium.recipeHash.size() < thaumatorium.maxRecipes) {
/* 69 */                   for (CrucibleRecipe cr : thaumatorium.recipes) {
/* 70 */                     if (cr.hash == message.hash) {
/* 71 */                       thaumatorium.recipeEssentia.add(cr.getAspects().copy());
/* 72 */                       thaumatorium.recipePlayer.add(entityPlayerMP.func_70005_c_());
/* 73 */                       thaumatorium.recipeHash.add(Integer.valueOf(cr.hash));
/* 74 */                       flag = true;
/*    */                       
/*    */                       break;
/*    */                     } 
/*    */                   } 
/*    */                 }
/* 80 */                 if (flag) {
/* 81 */                   thaumatorium.func_70296_d();
/* 82 */                   thaumatorium.syncTile(false);
/*    */                   
/*    */                   return;
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */           }
/*    */         });
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketSelectThaumotoriumRecipeToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */