/**
 * Created by Administrator on 2016/6/2.
 */
//���󼶱�Ĳ������----------������ҳ��ˢ��ʱ����ִ��
;(function($){
    var FileOut=function(){
        var self=this;
        //��ȡ����DOM��ҪΧ���ַ�����ʹ��
        self.pre_btn='.file-out .pre-btn';
        self.next_btn='.file-out .next-btn';
        self.root='.file-body';
        self.root_element='.body-floor';
        self.currentPopwin;
        self.nextPopwin;

        self.currentIndex;
        self.allIndex=self.getAllIndex();

        console.log("��ǰҳ��ȫ������:"+self.allIndex);
        console.log("���ò��");
        //����ҳ���е�Ԫ��index����
        self.setIndex();
        $(".body-floor .file-name span").click(function(event){
            event.stopPropagation();
            //��ȡ��ǰ��������λ��
            self.currentIndex=$(this).parents(self.root_element).attr("index");
            console.log("��ǰ���Ϊ��"+self.currentIndex+"��");
            self.renderDOM();            //��Ϊ������˳�����Կ��������˼ɵ�ʹ�ã���������
            self.carousel();          //���ֲ��¼������ǻ�û�����⻯,
        });

    };
    FileOut.prototype={
        //������Ⱦ����
        getAllIndex:function(){
            var self=this;
            return $(self.root).children(self.root_element).length;
        },
        setIndex:function(){
            var self=this;
            var num=1;
            $(self.root).children(self.root_element).each(function(){
                $(this).attr("index",num++);
            })
        },
        renderDOM:function(){
            var self=this;
            var strDom=['<div class="file-out" style="display: none;" >',
                '        <div class="pre-btn pre-bg"></div>',
                '        <!--<div class="clearfix"></div>-->',
                '        <div class="file-body">',
                '            <div class="content">',
                '                <div class="file">',
                '                    <div class="file-thumbnails">',
                '                        <div class="file-name">SQLdb_ilearn_3</div>',
                '                        <div class="file-class">�ļ�����SQL</div>',
                '                    </div>',
                '                    <div class="file-size">',
                '                        <button class="download">�鿴�ļ�(4MB)</button>',
                '                    </div>',
                '                </div>',
                '            </div>',
                '            <!--<div class="clearfix"></div>-->',
                '            <div class="attribute">',
                '                <div class="a-info">',
                '                    <div class="a-first">',
                '                        <div class="file-from">�����ļ���:���ݿ�</div>',
                '                        <div class="a-close">��</div>',
                '                        <div class="clearfix"></div>',
                '                    </div>',
                '                    <div class="file-name">SQLdb_ilearn_3</div>',
                '                    <div class="collected">�ղ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2333</div>',
                '                    <div class="a-third">',
                '                        <div class="file-uptime"><i class="fa fa-clock-o"></i>2013-12-12</div>',
                '                        <div class="file-people"><i class="fa fa-user"></i>�ϴ���-MR.Z</div>',
                '                    </div>',
                '                </div>',
                '                <div class="line"></div>',
                '                <div class="a-operate">',
                '                    <ul>',
                '                        <li><a href="#"><i class="fa fa-download"></i>&nbsp;&nbsp;����</a></li>',
                '                        <li><a href="#"><i class="fa fa-star"></i>&nbsp;&nbsp;�ղ�</a></li>',
                '                    </ul>',
                '                </div>',
                '                <div class="line"></div>',
                '                <div class="a-related">',
                '                    <ul>',
                '                        <li><a href="#"><i class="fa fa-link"></i>&nbsp;&nbsp;&nbsp;��������</a></li>',
                '                        <li><a href="#"><i class="fa fa-tag"></i>&nbsp;&nbsp;&nbsp;��ǩ</a></li>',
                '                    </ul>',
                '                </div>',
                '            </div>',
                '        </div>',
                '        <div class="next-btn"></div>',
                '        <!--<div class="clearfix"></div>-->',
                '    </div>'].join("");
            //���뵽body��
            $("body").append(strDom);           //������ô��¼��ǰ����������أ�
            self.currentPopwin=$(".file-out");       //��¼��ǰ����
            //��ʾ���������ֲ�
            $(".mask").fadeIn();
            $(".file-out").fadeIn();
            //Ϊclose��mask���¼�
            $(".a-close").on("click",function(event){
                event.stopPropagation();
                $(".file-out").fadeOut();
                $(".mask").fadeOut();
                self.currentPopwin.remove();
            })

            $(".mask").on("click",function(event){
                event.stopPropagation();
                $(".file-out").fadeOut();
                $(".mask").fadeOut();
            })
        },
        carousel:function(){
            var self=this;
            console.log("�ֲ�����");
            console.log("��ǰ�±�Ϊ"+self.currentIndex);
            //�жϿɲ�����next
            if(self.currentIndex!=self.allIndex){
                $(self.next_btn).css({
                    "display":"block"
                });
                $(this.next_btn).click(function(){
                    self.next();
                })
            }else{
                $(self.next_btn).css({
                    "display":"none"
                })
            }
            //�жϿɲ�����pre
            if(self.currentIndex!=1){
                /*$(self.pre_btn).css({
                    "display":"block"
                });*/
                $(self.pre_btn).addClass("pre-bg");
                $(this.pre_btn).click(function(){
                    self.pre();
                })
            }else{
                $(self.pre_btn).removeClass("pre-bg");
            }
        },
        next:function(){
            var self=this;
            //�ı��±�
            self.currentIndex++;
            //��֮ǰ��DOMĨȥ
            self.currentPopwin.fadeOut(200).remove();
            //չʾ��һ��DOM
            self.renderDOM();
            self.carousel();            //����һ������һ��DOM    �����������
            self.nextPopwin=$(".file-out");
            self.nextPopwin.fadeIn(200);
        },
        pre:function(){
            var self=this;
            //�ı��±�
            self.currentIndex--;
            //��֮ǰ��DOMĨȥ
            self.currentPopwin.fadeOut(200).remove();
            //չʾ��һ��DOM
            self.renderDOM();
            self.carousel();            //����һ������һ��DOM
            self.nextPopwin=$(".file-out");
            self.nextPopwin.fadeIn(200);
        }
    };
    window["FileOut"]=FileOut;
})(jQuery)