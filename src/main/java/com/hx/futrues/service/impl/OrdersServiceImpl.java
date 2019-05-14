package com.hx.futrues.service.impl;

import com.hx.futrues.common.Constants;
import com.hx.futrues.entity.Orders;
import com.hx.futrues.entity.Platform;
import com.hx.futrues.entity.Teacher;
import com.hx.futrues.entity.Variety;
import com.hx.futrues.exception.FutrueException;
import com.hx.futrues.repository.OrdersRepository;
import com.hx.futrues.repository.PlatformRepository;
import com.hx.futrues.repository.TeacherRepository;
import com.hx.futrues.repository.VarietyRepository;
import com.hx.futrues.service.IOrdersService;
import com.hx.futrues.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IWalletService walletService;

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private VarietyRepository varietyRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public Page<Orders> getOrdersList(Integer page, Integer size) {
        return this.ordersRepository.findAll(PageRequest.of(page - 1, size, new Sort(Sort.Direction.DESC, "id")));
    }

    @Override
    public boolean openingTransaction(Integer platformId, Integer type, Integer bbi, Integer number, BigDecimal startPoint, String time) throws FutrueException {
        Platform platform = this.platformRepository.getOne(platformId);
        if (platform == null) {
            throw new FutrueException("指定平台不存在");
        }

        Variety variety = varietyRepository.getOne(type);
        if (variety == null) {
            throw new FutrueException("指定品种不存在");
        }

        Orders orders = new Orders(platform, variety, bbi, number, time, startPoint);
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean offsetTransaction(Integer id, BigDecimal endPoint, String endTime, BigDecimal maxPoint, BigDecimal minPoint, String desc) throws FutrueException {
        Orders orders = this.ordersRepository.getOne(id);
        if (orders.getStatus() == 1) {
            return false;
        }
        orders.offsetTransaction(endPoint, endTime, maxPoint, minPoint, desc);
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean createOrders(Orders order) throws FutrueException {
        // 判断平台是否存在
        Platform platform = this.platformRepository.getOne(order.getPlatform().getId());
        if (platform == null) {
            throw new FutrueException("指定平台不存在");
        }

        // 判断品种是否存在
        Variety variety = varietyRepository.getOne(order.getVariety().getId());
        if (variety == null) {
            throw new FutrueException("指定品种不存在");
        }

        // 判断品种是否与平台对应
        if (!platform.getId().equals(variety.getPlatformId())) {
            throw new FutrueException("指定品种不存在");
        }

        // 判断带盘老师是否存在
        Teacher teacher = this.teacherRepository.getOne(order.getTeacher().getId());
        if (teacher == null) {
            throw new FutrueException("带盘老师不存在");
        }

        // 判断是否自己做单
        if (teacher.getId() != 1) {
            // 判断带盘老师是否与平台对应
            if (!platform.getId().equals(teacher.getPlatformId())) {
                throw new FutrueException("带盘老师不存在");
            }
        }

        // 计算盈亏
        order.countLoss(platform, variety);

        // 修改钱包金额
        this.walletService.changeCash(platform, Constants.CASH_TYPE_OFFSET, order.getLoss().subtract(order.getPoundage()), order.getId(), order.getEndTime());

        return true;
    }

    @Override
    public List<Orders> getOrdersCount(Integer type) {
        return null;
    }
}
