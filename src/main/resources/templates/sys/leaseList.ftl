<div class="page animation-fade page-tables">
	<div class="page-content">
		<div class="panel panel-bordered">
			<div class="panel-heading">
				<h3 class="panel-title">租赁信息列表</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<div class="search-panel">
						<form class="relForm">
                            <div class="form-group search-item">
                                <div class="input-group">
                                    <input name="lesseeName" type="text" class="form-control" placeholder="承租人姓名"/>
                                </div>
                            </div>
							<button type="button" class="btn btn-default btn-outline btn-lg form-group"
									style="font-size: 12px;" onclick="freshList()">搜索
							</button>
						</form>
					</div>
					<div class="clearfix">
						<button type="button" class="btn btn-outline btn-primary btn-sm"
								style="border-radius: 0;float: left" onclick="add()">
							<i class="icon wb-plus" aria-hidden="true"></i> 添加
						</button>
					</div>
					<table class="table table-hover table-bordered">
						<thead>
						<tr>
							<th class="text-nowrap">承租人姓名</th>
                            <th class="text-nowrap">电话</th>
							<th class="text-nowrap">所属公司</th>
							<th class="text-nowrap">集装箱编码</th>
							<th class="text-nowrap">空调编码</th>
							<th class="text-nowrap">活动床编码</th>
							<th class="text-nowrap">押金金额</th>
							<th class="text-nowrap">租赁时间</th>
							<th class="text-nowrap">结束时间</th>
							<th class="text-nowrap">当前费用</th>
							<th class="text-nowrap">状态</th>
                            <th class="text-nowrap">操作</th>
						</tr>
						</thead>
						<tbody></tbody>
					</table>
					<#include "/layout/pageBar.ftl">
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade modal-fade-in-scale-up" id="leaseWin" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-center">
		<form class="modal-content " id="leaseForm">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>×</span></button>
				<h4 class="modal-title">租赁信息录入</h4>
			</div>
			<div class="modal-body">
				<div class="row">
                    <input type="hidden" name="id" value="">
					<div class="col-lg-12 form-group">
						<label class="col-lg-3 control-label"><span class="required">* </span>承租人姓名</label>
						<div class="col-lg-9">
							<input type="text" name="lesseeName" class="form-control" maxlength="20" required/>
						</div>
					</div>
                    <div class="col-lg-12 form-group">
                        <label class="col-lg-3 control-label"><span class="required">* </span>联系电话</label>
                        <div class="col-lg-9">
                            <input type="text" name="lesseeTel" class="form-control" maxlength="20" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label class="col-lg-3 control-label"><span class="required">* </span>所属公司</label>
                        <div class="col-lg-9">
                            <input type="text" name="lesseeCompany" class="form-control" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label class="col-lg-3 control-label">集装箱编码</label>
                        <div class="col-lg-9">
                            <input type="text" name="boxCode" class="form-control" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group" >
                        <label class="col-lg-3 control-label">空调编码</label>
                        <div class="col-lg-9">
                            <input type="text" name="airCode" class="form-control" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label class="col-lg-3 control-label">活动床编码</label>
                        <div class="col-lg-9">
                            <input type="text" name="bedCode" class="form-control" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label class="col-lg-3 control-label"><span class="required">* </span>押金金额</label>
                        <div class="col-lg-9">
                            <input type="text" name="cashPledge" class="form-control" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="col-lg-12 form-group" >
                        <label class="col-lg-3 control-label"><span class="required">* </span>租赁时间</label>
                        <div class="col-lg-9">
                            <input name="startDate" type="text" class="form-control" data-plugin="datepicker" />
                        </div>
                    </div>
                    <div class="col-lg-12 form-group" >
                        <label class="col-lg-3 control-label"><span class="required">* </span>结束时间</label>
                        <div class="col-lg-9">
                            <input name="endDate" type="text" class="form-control" data-plugin="datepicker" />
                        </div>
                    </div>
					<div class="col-lg-12 form-group btngroup">
						<button type="button" class="btn btn-primary col-lg-2" onclick="save()">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="../../js/sys/leaseList.js"></script>
