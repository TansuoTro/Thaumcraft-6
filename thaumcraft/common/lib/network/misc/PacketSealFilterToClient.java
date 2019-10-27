/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*    */ import thaumcraft.api.golems.seals.ISealEntity;
/*    */ import thaumcraft.api.golems.seals.SealPos;
/*    */ import thaumcraft.common.golems.seals.SealHandler;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ 
/*    */ public class PacketSealFilterToClient
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketSealFilterToClient, IMessage>
/*    */ {
/*    */   BlockPos pos;
/*    */   EnumFacing face;
/*    */   byte filtersize;
/*    */   NonNullList<ItemStack> filter;
/*    */   NonNullList<Integer> filterStackSize;
/*    */   
/*    */   public PacketSealFilterToClient() {}
/*    */   
/*    */   public PacketSealFilterToClient(ISealEntity se) {
/* 32 */     this.pos = (se.getSealPos()).pos;
/* 33 */     this.face = (se.getSealPos()).face;
/*    */     
/* 35 */     if (se.getSeal() != null && se.getSeal() instanceof ISealConfigFilter) {
/* 36 */       ISealConfigFilter cp = (ISealConfigFilter)se.getSeal();
/* 37 */       this.filtersize = (byte)cp.getFilterSize();
/* 38 */       this.filter = cp.getInv();
/* 39 */       this.filterStackSize = cp.getSizes();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf dos) {
/* 45 */     dos.writeLong(this.pos.func_177986_g());
/* 46 */     dos.writeByte(this.face.ordinal());
/* 47 */     dos.writeByte(this.filtersize);
/* 48 */     for (int a = 0; a < this.filtersize; a++) {
/* 49 */       Utils.writeItemStackToBuffer(dos, (ItemStack)this.filter.get(a));
/* 50 */       dos.writeShort(((Integer)this.filterStackSize.get(a)).intValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf dat) {
/* 56 */     this.pos = BlockPos.func_177969_a(dat.readLong());
/* 57 */     this.face = EnumFacing.field_82609_l[dat.readByte()];
/* 58 */     this.filtersize = dat.readByte();
/* 59 */     this.filter = NonNullList.func_191197_a(this.filtersize, ItemStack.field_190927_a);
/* 60 */     this.filterStackSize = NonNullList.func_191197_a(this.filtersize, Integer.valueOf(0));
/* 61 */     for (int a = 0; a < this.filtersize; a++) {
/* 62 */       this.filter.set(a, Utils.readItemStackFromBuffer(dat));
/* 63 */       this.filterStackSize.set(a, Integer.valueOf(dat.readShort()));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketSealFilterToClient message, MessageContext ctx) {
/*    */     try {
/* 71 */       ISealEntity seal = SealHandler.getSealEntity((Thaumcraft.proxy.getClientWorld()).field_73011_w.getDimension(), new SealPos(message.pos, message.face));
/* 72 */       if (seal != null && seal.getSeal() instanceof ISealConfigFilter) {
/* 73 */         ISealConfigFilter cp = (ISealConfigFilter)seal.getSeal();
/* 74 */         for (int a = 0; a < message.filtersize; a++) {
/* 75 */           cp.setFilterSlot(a, (ItemStack)message.filter.get(a));
/* 76 */           cp.setFilterSlotSize(a, ((Integer)message.filterStackSize.get(a)).intValue());
/*    */         } 
/*    */       } 
/* 79 */     } catch (Exception e) {
/* 80 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketSealFilterToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */